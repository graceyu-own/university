package org.techas.falleaves.security.service;

import org.techas.falleaves.model.UserEntity;
import org.techas.falleaves.model.UserInfoEntity;
import org.techas.falleaves.model.UserLoginEntity;

public interface ISecurityService {

    UserEntity findUserById(Integer id);

    UserInfoEntity findUserInfoById(Integer id);

    UserInfoEntity findUserInfoByUid(Integer uid);

    UserInfoEntity findUserInfoByNickname(String nickname);

    UserInfoEntity findUserInfoByEmail(String email);

    UserInfoEntity findUserInfoByPhone(String phone);

    UserLoginEntity findUserLoginById(Integer id);

    UserLoginEntity findUserLoginByUid(Integer uid);

    UserLoginEntity findUserLoginByIdentifier(String identifier);

}
