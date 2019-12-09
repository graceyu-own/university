package org.techas.falleaves.services.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.techas.falleaves.model.UserLoginEntity;
import org.techas.falleaves.model.dto.UserLoginDTO;
import org.techas.falleaves.model.vo.UserLoginVO;
import org.techas.falleaves.model.vo.UserRegisterVO;
import org.techas.falleaves.services.auth.service.impl.AuthService;
import org.techas.falleaves.utils.ResponseData;
import org.techas.falleaves.utils.ReturnValue;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @RequestMapping("/login")
    public ResponseData<String> login(UserLoginVO userLoginVO){

        if (null == userLoginVO.getIdentifier() || userLoginVO.getIdentifier().equals("")) {
            return ResponseData.New(ReturnValue.IDENTIFIER_EMPTY);
        }

        if (null == userLoginVO.getCredential() || userLoginVO.getCredential().equals("")) {
            return ResponseData.New(ReturnValue.CREDENTIAL_EMPTY);
        }

        try {
            UserLoginEntity result = authService.login(
                    new UserLoginDTO()
                        .setIdentifier(userLoginVO.getIdentifier())
                        .setCredential(userLoginVO.getCredential())
            );

            if(null == result) {
                return ResponseData.New(ReturnValue.INCORRECT_CREDENTIALS);
            }

            return  ResponseData.New(ReturnValue.SUCCESS);

        } catch (Exception ignore) {
            return ResponseData.New(ReturnValue.INTERNET_SERVER_ERROR);
        }
    }

    @RequestMapping("/register")
    public ResponseData<String> register(UserRegisterVO userRegisterVO) {
        return new ResponseData<>(0.0, "null");
    }

    @RequestMapping("/logout")
    public ResponseData<String> logout() {
        return new ResponseData<>(0.0, "null");
    }

}