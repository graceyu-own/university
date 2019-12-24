package org.techas.falleaves.services.auth.service;

import org.techas.falleaves.model.UserEntity;
import org.techas.falleaves.model.UserLoginEntity;

public interface IAuthService {

    UserLoginEntity login(String identifier, String credential);

    UserEntity register(String nickname, String email, String password);

    boolean sendRegisterMail(String email);

    String getRegisterMail(String email);

    void deleteRegisterMail(String email);

    boolean sendResetPasswordMail(String email);

    String getResetPasswordMail(String email);

    void deleteResetPasswordMail(String email);

    void setPassword(String email, String password);
}