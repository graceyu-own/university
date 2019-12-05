package cn.falleaves.auth.controller;

import cn.falleaves.auth.model.dto.UserLoginDTO;
import cn.falleaves.auth.model.vo.UserLoginVO;
import cn.falleaves.auth.model.vo.UserRegisterVO;
import cn.falleaves.auth.service.impl.AuthService;
import cn.falleaves.auth.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/login")
    public ResponseData login(UserLoginVO userLoginVO) {

        ResponseData rd = new ResponseData();

        if(null == userLoginVO.getIdentifier() || userLoginVO.getIdentifier().equals("")) {
            rd.setAll(401, 1, "Identifier cannot be null");
        } else

        if(null == userLoginVO.getCredential() || userLoginVO.getCredential().equals("")) {
            rd.setAll(401, 2, "Credential cannot be null");
        }

        if(!authService.login(new UserLoginDTO(userLoginVO.getIdentifier(), userLoginVO.getCredential()))) {
            rd.setAll(401, 3, "Incorrect credentials");
        }

        rd.setAll(400, 0, "Login success");

        return rd;
    }

    @RequestMapping("/register")
    public ResponseData register(UserRegisterVO userRegisterVO) {

        ResponseData rd = new ResponseData();

        return rd;
    }

    @RequestMapping("/logout")
    public ResponseData logout() {

        ResponseData rd = new ResponseData();

        return rd;
    }
}
