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
import org.techas.falleaves.model.vo.EmailVO;
import org.techas.falleaves.model.vo.UserLoginVO;
import org.techas.falleaves.model.vo.UserRegisterVO;
import org.techas.falleaves.services.auth.service.impl.AuthService;
import org.techas.falleaves.utils.ResponseData;
import org.techas.falleaves.utils.HttpCode;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/login")
    public ResponseData<String> login(UserLoginVO userLoginVO){

        if (null == userLoginVO.getIdentifier() || userLoginVO.getIdentifier().equals("")) {
            return ResponseData.New(HttpCode.IDENTIFIER_EMPTY, null);
        }

        if (null == userLoginVO.getCredential() || userLoginVO.getCredential().equals("")) {
            return ResponseData.New(HttpCode.CREDENTIAL_EMPTY, null);
        }

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(userLoginVO.getIdentifier(), userLoginVO.getCredential()));
            return ResponseData.New(HttpCode.SUCCESS, null);

        } catch (UnknownAccountException ignore) {
            return ResponseData.New(HttpCode.ACCOUNT_NOT_EXISTS, null);
        } catch (IncorrectCredentialsException ignore) {
            return ResponseData.New(HttpCode.INCORRECT_CREDENTIALS, null);
        } catch (LockedAccountException ignore) {
            return ResponseData.New(HttpCode.ACCOUNT_LOCKED, null);
        } catch (Exception ignore) {
            return ResponseData.New(HttpCode.INTERNET_SERVER_ERROR, null);
        }
    }

    @RequestMapping("/register")
    public ResponseData<String> register(UserRegisterVO userRegisterVO) {

        if(null == userRegisterVO.getNickname() || userRegisterVO.getNickname().equals("")) {
            return ResponseData.New(HttpCode.NICKNAME_EMPTY, null);
        }

        if(null == userRegisterVO.getEmail() || userRegisterVO.getEmail().equals("")) {
            return ResponseData.New(HttpCode.EMAIL_EMPTY, null);
        }

        if(null == userRegisterVO.getPassword() || userRegisterVO.getPassword().equals("")) {
            return ResponseData.New(HttpCode.PASSWORD_EMPTY, null);
        }

        if(null == userRegisterVO.getPassword2() || userRegisterVO.getPassword2().equals("")) {
            return ResponseData.New(HttpCode.PASSWORD2_EMPTY, null);
        }

        if(!userRegisterVO.getPassword().equals(userRegisterVO.getPassword2())) {
            return ResponseData.New(HttpCode.TWOPASSWORD_NOTMATCH, null);
        }

        if(null == userRegisterVO.getEmailValid() || userRegisterVO.getEmailValid().equals("")) {
            return ResponseData.New(HttpCode.EMAILVALID_EMPTY, null);
        }



        return ResponseData.New(HttpCode.SUCCESS, null);
    }

    public ResponseData<String> sendEmailValid(EmailVO emailValidVO) {

        if(null == emailValidVO.getEmail() || emailValidVO.getEmail().equals("")) {
            return ResponseData.New(HttpCode.EMAIL_EMPTY, null);
        }

        return ResponseData.New(HttpCode.SUCCESS, null);

    }

    @RequestMapping("/logout")
    public ResponseData<String> logout() {
        return ResponseData.New(HttpCode.SUCCESS, null);
    }

}