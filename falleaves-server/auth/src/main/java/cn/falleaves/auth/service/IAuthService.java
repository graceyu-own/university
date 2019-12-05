package cn.falleaves.auth.service;

import cn.falleaves.auth.model.dto.UserLoginDTO;
import cn.falleaves.auth.model.dto.UserRegisterDTO;
import org.springframework.stereotype.Service;

public interface IAuthService {

    boolean login(UserLoginDTO userLoginDTO) throws Exception;

    boolean register(UserRegisterDTO userRegisterDTO) throws Exception;

    boolean logout() throws Exception;

}
