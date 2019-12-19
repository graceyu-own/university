package org.techas.falleaves.services.auth.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.techas.falleaves.model.dto.EmailDTO;
import org.techas.falleaves.model.dto.UserRegisterDTO;
import org.techas.falleaves.model.vo.EmailVO;
import org.techas.falleaves.model.vo.UserLoginVO;
import org.techas.falleaves.model.vo.UserRegisterVO;
import org.techas.falleaves.security.service.ISecurityService;
import org.techas.falleaves.services.auth.service.IAuthService;
import org.techas.falleaves.services.auth.service.IBehaviorService;
import org.techas.falleaves.services.auth.service.impl.AuthService;
import org.techas.falleaves.services.auth.service.impl.BehaviorService;
import org.techas.falleaves.utils.Regex;
import org.techas.falleaves.utils.ResponseData;
import org.techas.falleaves.utils.HttpCode;

@RestController
public class AuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IBehaviorService behaviorService;

    @Autowired
    private ISecurityService securityService;

    @RequestMapping("/login")
    public ResponseData<String> login(UserLoginVO userLoginVO){

        if (null == userLoginVO.getIdentifier() || userLoginVO.getIdentifier().equals("")) {
            return ResponseData.New(HttpCode.AUTH_LOGIN__IDENTIFIER_EMPTY, "Nickname cannot be empty.");
        }

        if (null == userLoginVO.getCredential() || userLoginVO.getCredential().equals("")) {
            return ResponseData.New(HttpCode.AUTH_LOGIN__CREDENTIAL_EMPTY, "Password cannot be empty.");
        }

        if(null == userLoginVO.getBehaviorValid() || userLoginVO.getBehaviorValid().equals("")) {
            return ResponseData.New(HttpCode.BEHAVIOR_VALID__FAILED, "Behavior verification failed.");
        }

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(userLoginVO.getIdentifier(), userLoginVO.getCredential()));
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

    @RequestMapping("/register")
    public ResponseData<String> register(UserRegisterVO userRegisterVO) {

        // check 用户名是否为空
        if(null == userRegisterVO.getNickname() || userRegisterVO.getNickname().equals("")) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__NICKNAME_EMPTY, "Nickname cannot be empty.");
        }

        // check 邮箱是否为空
        if(null == userRegisterVO.getEmail() || userRegisterVO.getEmail().equals("")) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__EMAIL_EMPTY, "Email cannot be empty.");
        }

        // check 密码是否为空
        if(null == userRegisterVO.getPassword() || userRegisterVO.getPassword().equals("")) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__PASSWORD_EMPTY, "Password cannot be empty");
        }

        // check 二次密码是否为空
        if(null == userRegisterVO.getPassword2() || userRegisterVO.getPassword2().equals("")) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__PASSWORD2_EMPTY, "Two password not match.");
        }

        // check 两次密码是否匹配
        if(!userRegisterVO.getPassword().equals(userRegisterVO.getPassword2())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__TWO_PASSWORD_NOTMATCH, "Two password not match.");
        }

        // check 邮箱验证码是否为空
        if(null == userRegisterVO.getEmailValid() || userRegisterVO.getEmailValid().equals("")) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__EMAIL_VALID_EMPTY, "Email valid code cannot be empty.");
        }

        // check 用户名是否合法
        if(!userRegisterVO.getNickname().matches(Regex.NICKNAME.getPattern())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__NICKNAME_REGEX_FAILED, "Illegal nickname");
        }

        // check 邮箱是否合法
        if(!userRegisterVO.getEmail().matches(Regex.EMAIL.getPattern())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER_MAIL__EMAIL_REGEX_FAILED, "Is not a valid email address.");
        }

        // check 密码是否合法(无需再次判断二次密码|因为验证过两次密码是否匹配)
        if(!userRegisterVO.getPassword().matches(Regex.PASSWORD.getPattern())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__PASSWORD_REGEX_FAILED, "Illegal password.");
        }

        // check 检测邮箱验证码是否正确
        String code = authService.getRegisterMail(new EmailDTO().setEmail(userRegisterVO.getEmail()));
        if(null == code || !code.equals(userRegisterVO.getEmailValid())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__EMAIL_VALID_FAILED, "Email verification code error.");
        }

        // check 是否存在用户名
        if(securityService.hasNickname(userRegisterVO.getNickname())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__NICKNAME_EXISTS, "User name already exists.");
        }

        // check 是否存在邮箱
        if(securityService.hasEmail(userRegisterVO.getEmail())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER__EMAIL_EXISTS, "Mailbox already exists.");
        }

        // check 注册是否失败
        if(null == authService.register(new UserRegisterDTO()
            .setNickname(userRegisterVO.getNickname())
            .setEmail(userRegisterVO.getEmail())
            .setPassword(userRegisterVO.getPassword())
        )) {
            return ResponseData.New(HttpCode.COMMON__INTERNET_SERVER_ERROR, "Unknown error.");
        }

        // check -> ALL OK
        return ResponseData.New(HttpCode.COMMON__SUCCESS, "Register success.");
    }

    @RequestMapping("/sendRegisterMail")
    public ResponseData<String> sendRegisterMail(EmailVO emailVO) {

        // check 邮箱是否为空
        if(null == emailVO.getEmail() || emailVO.getEmail().equals("")) {
            return ResponseData.New(HttpCode.AUTH_REGISTER_MAIL__EMAIL_EMPTY, "Email cannot be empty.");
        }

        // check 邮箱是否合法
        if(!emailVO.getEmail().matches(Regex.EMAIL.getPattern())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER_MAIL__EMAIL_REGEX_FAILED, "Is not a valid email address.");
        }

        // check 是否发送繁忙
        if(securityService.isFreqMailByEmail(emailVO.getEmail()) || securityService.isFreqMailBySession((String)SecurityUtils.getSubject().getSession().getId())){
            return ResponseData.New(HttpCode.AUTH_REGISTER_MAIL__MAIL_REQUEST_FREQUENTLY, "Too many requests.");
        }

        // check 是否存在邮箱
        if(securityService.hasEmail(emailVO.getEmail())) {
            return ResponseData.New(HttpCode.AUTH_REGISTER_MAIL__EMAIL_EXISTS, "This email address is already in use.");
        }

        // check 发送是否失败
        if(!authService.sendRegisterMail(new EmailDTO().setEmail(emailVO.getEmail()))){
            return ResponseData.New(HttpCode.COMMON__INTERNET_SERVER_ERROR, "Unknown error.");
        }

        // 设置最后发送事时间
        securityService.setLastSendMailTimeByEmail(emailVO.getEmail());
        securityService.setLastSendMailTimeBySession((String) SecurityUtils.getSubject().getSession().getId());

        return ResponseData.New(HttpCode.COMMON__SUCCESS, "An email to send to your mailbox.");

    }
}