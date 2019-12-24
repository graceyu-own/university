package org.techas.falleaves.services.auth.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.techas.falleaves.security.service.ISecurityService;
import org.techas.falleaves.services.auth.service.IAuthService;
import org.techas.falleaves.utils.*;

import javax.annotation.Resource;
import java.util.Base64;

@RestController
public class AuthController {

    @Resource
    private IAuthService authService;

    @Resource
    private ISecurityService securityService;

    /**
     *
     * @param identifier        登录标识符, 可能是用户名, 邮箱, 手机号
     * @param credential        登录凭证, 一般是密码
     * @param behaviorValid     登录行为验证, 目前仅为行为验证结果
     *
     * @return  ResponseData
     */
    @RequestMapping("/login")
    public ResponseData<String> login(
        @RequestParam(value = "identifier", defaultValue = "") String identifier,
        @RequestParam(value = "credential", defaultValue = "") String credential,
        @RequestParam(value = "behaviorValid", defaultValue = "") String behaviorValid
    ){

        if (null == identifier || identifier.equals("")) {
            return ResponseData.New(HttpCode.AUTH_LOGIN__IDENTIFIER_EMPTY, "Nickname cannot be empty.");
        }

        if (null == credential || credential.equals("")) {
            return ResponseData.New(HttpCode.AUTH_LOGIN__CREDENTIAL_EMPTY, "Password cannot be empty.");
        }

        if(null == behaviorValid || behaviorValid.equals("")) {
            return ResponseData.New(HttpCode.BEHAVIOR_VALID__FAILED, "Behavior verification failed.");
        }

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(identifier, credential));
            return ResponseData.New(HttpCode.COMMON__SUCCESS, "Login success.");

        } catch (UnknownAccountException ignore) {
            return ResponseData.New(HttpCode.AUTH_LOGIN__ACCOUNT_NOT_EXISTS, "Incorrect credentials.");
        } catch (IncorrectCredentialsException ignore) {
            return ResponseData.New(HttpCode.AUTH_LOGIN__INCORRECT_CREDENTIALS, "Incorrect credentials.");
        } catch (LockedAccountException ignore) {
            return ResponseData.New(HttpCode.AUTH_LOGIN__ACCOUNT_LOCKED, "Account already locked.");
        } catch (Exception ignore) {
            return ResponseData.New(HttpCode.COMMON__INTERNET_SERVER_ERROR, "Unknown error.");
        }
    }

    /**
     *
     * @param nickname      用户昵称(unique), 也作为默认的登录标识符
     * @param email         用户邮箱
     * @param emailValid    用户邮箱验证码
     * @param password      用户密码
     * @param password2     用户再次输入密码(防止用户输错密码)
     *
     * @return  ResponseData
     */
    @RequestMapping("/register")
    public ResponseData<String> register(
        String nickname,
        String email,
        String emailValid,
        String password,
        String password2
    ) {

        // check 用户名是否为空
        if("".equals(nickname)) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__NICKNAME_EMPTY, "Nickname cannot be empty.");
        }

        // check 邮箱是否为空
        if("".equals(email)) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__EMAIL_EMPTY, "Email cannot be empty.");
        }

        // check 密码是否为空
        if("".equals(password)) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__PASSWORD_EMPTY, "Password cannot be empty");
        }

        // check 二次密码是否为空
        if("".equals(password2)) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__PASSWORD2_EMPTY, "Two password not match.");
        }

        // check 两次密码是否匹配
        if(!password.equals(password2)) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__TWO_PASSWORD_NOTMATCH, "Two password not match.");
        }

        // check 邮箱验证码是否为空
        if("".equals(emailValid)) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__EMAIL_VALID_EMPTY, "Email valid code cannot be empty.");
        }

        // check 用户名是否合法
        if(!nickname.matches(Regex.NICKNAME.getPattern())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__NICKNAME_REGEX_FAILED, "Illegal nickname");
        }

        // check 邮箱是否合法
        if(!email.matches(Regex.EMAIL.getPattern())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER_MAIL__EMAIL_REGEX_FAILED, "Is not a valid email address.");
        }

        // check 密码是否合法(无需再次判断二次密码|因为验证过两次密码是否匹配)
        if(!password.matches(Regex.PASSWORD.getPattern())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__PASSWORD_REGEX_FAILED, "Illegal password.");
        }

        // check 检测邮箱验证码是否正确
        String code = authService.getRegisterMail(email);
        if(!emailValid.equals(code)) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__EMAIL_VALID_FAILED, "Email verification code error.");
        }

        // check 是否存在用户名
        if(securityService.hasNickname(nickname)) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__NICKNAME_EXISTS, "User name already exists.");
        }

        // check 是否存在邮箱
        if(securityService.hasEmail(email)) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__EMAIL_EXISTS, "Email already exists.");
        }

        // check 注册是否失败
        if(null == authService.register(nickname, email, password)) {
            return ResponseData.New(HttpCode.COMMON__INTERNET_SERVER_ERROR, "Unknown error.");
        }

        // 删除注册邮件缓存, 防止下次使用
        authService.deleteRegisterMail(email);

        // check -> ALL OK
        return ResponseData.New(HttpCode.COMMON__SUCCESS, "Register success.");

        // TODO: 2019/12/23 刚写完找回密码服务, 待验证是否有bug 
    }

    /**
     *
     * @param email     发送邮箱
     *
     * @return  ResponseData
     */
    @RequestMapping("/sendRegisterMail")
    public ResponseData<String> sendRegisterMail(String email) {

        // check 邮箱是否为空
        if("".equals(email)) {
            return ResponseData.New(HttpCode.AUTH_REGISTER_MAIL__EMAIL_EMPTY, "Email cannot be empty.");
        }

        // check 邮箱是否合法
        if(!email.matches(Regex.EMAIL.getPattern())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER_MAIL__EMAIL_REGEX_FAILED, "Is not a valid email address.");
        }

        // check 是否发送繁忙
        if(securityService.isFreqMailByEmail(email) || securityService.isFreqMailBySession((String)SecurityUtils.getSubject().getSession().getId())){
            return ResponseData.New(HttpCode.AUTH_REGISTER_MAIL__MAIL_REQUEST_FREQUENTLY, "Too many requests.");
        }

        // check 是否存在邮箱
        if(securityService.hasEmail(email)) {
            return ResponseData.New(HttpCode.AUTH_REGISTER_MAIL__EMAIL_EXISTS, "This email address is already in use.");
        }

        // check 发送是否失败
        if(!authService.sendRegisterMail(email)){
            return ResponseData.New(HttpCode.COMMON__INTERNET_SERVER_ERROR, "Unknown error.");
        }

        // 设置最后发送时间
        securityService.setLastSendMailTimeByEmail(email);
        securityService.setLastSendMailTimeBySession((String) SecurityUtils.getSubject().getSession().getId());

        return ResponseData.New(HttpCode.COMMON__SUCCESS, "An email to send to your mailbox.");
    }

    /**
     *
     * @param email     所绑定的邮箱
     *
     * @return  ResponseData
     */
    @RequestMapping("/resetPassword")
    public ResponseData<String> resetPassword(String email) {

        // check 邮箱是否为空
        if("".equals(email)) {
            return ResponseData.New(HttpCode.AUTH_FORGETPASSWORD__EMAIL_EMPTY, "Email cannot be empty.");
        }

        // check 是否存在邮箱
        if(!securityService.hasEmail(email)) {
            return ResponseData.New(HttpCode.AUTH_FORGETPASSWORD__EMAIL_NOT_EXISTS, "Email not exists.");
        }

        // check 是否发送繁忙
        if(securityService.isFreqMailByEmail(email) || securityService.isFreqMailBySession((String)SecurityUtils.getSubject().getSession().getId())){
            return ResponseData.New(HttpCode.AUTH_REGISTER_MAIL__MAIL_REQUEST_FREQUENTLY, "Too many requests.");
        }

        // check 是否发送失败
        if(!authService.sendResetPasswordMail(email)) {
            return ResponseData.New(HttpCode.COMMON__INTERNET_SERVER_ERROR, "Unknown error.");
        }

        // 设置最后发送时间
        securityService.setLastSendMailTimeByEmail(email);
        securityService.setLastSendMailTimeBySession((String) SecurityUtils.getSubject().getSession().getId());
        return ResponseData.New(HttpCode.COMMON__SUCCESS, "An email to send to your mailbox.");
    }

    /**
     *
     * @param data 邮箱url后面的数据, 包含邮箱, 发送时间等数据
     *
     * @return ResponseData
     */
    @RequestMapping("/setPassword")
    public ResponseData<String> setPassword(String data) {

        if("".equals(data)) {
            return ResponseData.New(HttpCode.AUTH_SETPASSWORD__DATA_EMPTY, "Data cannot be empty.");
        }

        String plainText = DesUtils.decodeBase64(data);
        String[] params;

        if(null == plainText || (params = plainText.split("&")).length != 2) {
            return ResponseData.New(HttpCode.AUTH_SETPASSWORD__DATA_EXCEPT, "Data except.");
        }

        String email = Utils.getUrlParam(params[0]);
        String emailValid = Utils.getUrlParam(params[1]);

        if(null == email || null == emailValid) {
            return ResponseData.New(HttpCode.AUTH_SETPASSWORD__DATA_EXCEPT, "Data except.");
        }

        String rEmailValid = authService.getResetPasswordMail(email);
        if(null == rEmailValid || !rEmailValid.equals(emailValid)) {
            return ResponseData.New(HttpCode.AUTH_SETPASSWORD__EXPIRE, "This url already expire.");
        }

        // 删除找回密码邮件的缓存, 防止下次使用
        authService.deleteRegisterMail(email);

        //重设用户密码


        return ResponseData.New(HttpCode.COMMON__SUCCESS, "Set password success.");

    }

}