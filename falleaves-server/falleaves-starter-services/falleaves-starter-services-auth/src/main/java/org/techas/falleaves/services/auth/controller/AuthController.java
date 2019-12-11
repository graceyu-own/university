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
import org.techas.falleaves.model.vo.UserLoginVO;
import org.techas.falleaves.model.vo.UserRegisterVO;
import org.techas.falleaves.services.auth.service.impl.AuthService;
import org.techas.falleaves.utils.ResponseData;
import org.techas.falleaves.utils.ReturnValue;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/login")
    public ResponseData<String> login(UserLoginVO userLoginVO){

        if (null == userLoginVO.getIdentifier() || userLoginVO.getIdentifier().equals("")) {
            return ResponseData.New(ReturnValue.IDENTIFIER_EMPTY, null);
        }

        if (null == userLoginVO.getCredential() || userLoginVO.getCredential().equals("")) {
            return ResponseData.New(ReturnValue.CREDENTIAL_EMPTY, null);
        }

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(userLoginVO.getIdentifier(), userLoginVO.getCredential()));
            return ResponseData.New(ReturnValue.SUCCESS, null);

        } catch (UnknownAccountException ignore) {
            return ResponseData.New(ReturnValue.ACCOUNT_NOT_EXISTS, null);
        } catch (IncorrectCredentialsException ignore) {
            return ResponseData.New(ReturnValue.INCORRECT_CREDENTIALS, null);
        } catch (LockedAccountException ignore) {
            return ResponseData.New(ReturnValue.ACCOUNT_LOCKED, null);
        } catch (Exception ignore) {
            return ResponseData.New(ReturnValue.INTERNET_SERVER_ERROR, null);
        }
    }

    @RequestMapping("/register")
    public ResponseData<String> register(UserRegisterVO userRegisterVO) {
        return ResponseData.New(ReturnValue.SUCCESS, null);
    }

    @RequestMapping("/logout")
    public ResponseData<String> logout() {
        return ResponseData.New(ReturnValue.SUCCESS, null);
    }

}