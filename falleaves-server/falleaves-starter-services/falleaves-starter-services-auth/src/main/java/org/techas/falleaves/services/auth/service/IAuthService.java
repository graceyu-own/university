package org.techas.falleaves.services.auth.service;

import org.apache.shiro.authz.AuthorizationException;
import org.techas.falleaves.model.UserEntity;
import org.techas.falleaves.model.UserLoginEntity;

public interface IAuthService {

    void login(String identifier, String credential) throws AuthorizationException;

    boolean register(String nickname, String email, String password);

    boolean sendRegisterMail(String email);

    String getRegisterMail(String email);

    void deleteRegisterMail(String email);

    boolean sendResetPasswordMail(String email);

    String getResetPasswordMail(String email);

    void deleteResetPasswordMail(String email);

    void setPassword(String email, String password);
}