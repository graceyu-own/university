package cn.falleaves.auth.controller;

import cn.falleaves.auth.model.dto.UserLoginDTO;
import cn.falleaves.auth.model.vo.UserLoginVO;
import cn.falleaves.auth.model.vo.UserRegisterVO;
import cn.falleaves.auth.service.impl.AuthService;
import cn.falleaves.common.ResponseData;
import cn.falleaves.common.ReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/login")
    public ResponseData<? extends String> login(UserLoginVO userLoginVO) {

        if(null == userLoginVO.getIdentifier() || userLoginVO.getIdentifier().equals("")) {
            return ResponseData.New(ReturnValue.IDENTIFIER_EMPTY);
        }

        if(null == userLoginVO.getCredential() || userLoginVO.getCredential().equals("")) {
            return ResponseData.New(ReturnValue.CREDENTIAL_EMPTY);
        }

        try{
            boolean result = authService.login(new UserLoginDTO(userLoginVO.getIdentifier(), userLoginVO.getCredential()));
            if(!result) {
                return ResponseData.New(ReturnValue.INCORRECT_CREDENTIALS);
            }
            return ResponseData.New(ReturnValue.SUCCESS);
        } catch (Exception e) {
            return ResponseData.New(ReturnValue.INTERNET_SERVER_ERROR);
        }
    }

    @RequestMapping("/register")
    public ResponseData register(UserRegisterVO userRegisterVO) {

        ResponseData rd = new ResponseData();

        if(null == userRegisterVO.getNickname() || userRegisterVO.getNickname().equals("")) {
            return ResponseData.New(401.10, "Nickname is empty");
        }

        
        return rd;
    }

    @RequestMapping("/logout")
    public ResponseData logout() {

        ResponseData rd = new ResponseData();



        return rd;
    }
}
