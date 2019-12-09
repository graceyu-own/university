package org.techas.falleaves.services.auth.service;

import org.techas.falleaves.model.UserEntity;
import org.techas.falleaves.model.UserLoginEntity;
import org.techas.falleaves.model.dto.UserLoginDTO;
import org.techas.falleaves.model.dto.UserRegisterDTO;

public interface IAuthService {

    UserLoginEntity login(UserLoginDTO userLoginDTO);

    UserEntity register(UserRegisterDTO userRegisterDTO);

    boolean logout();
}