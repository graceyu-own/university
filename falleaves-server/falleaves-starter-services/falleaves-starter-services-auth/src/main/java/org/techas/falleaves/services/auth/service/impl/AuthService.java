package org.techas.falleaves.services.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.techas.falleaves.dao.UserLoginRepository;
import org.techas.falleaves.dao.UserRepository;
import org.techas.falleaves.model.UserEntity;
import org.techas.falleaves.model.UserLoginEntity;
import org.techas.falleaves.model.dto.UserLoginDTO;
import org.techas.falleaves.model.dto.UserRegisterDTO;
import org.techas.falleaves.services.auth.service.IAuthService;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class AuthService implements IAuthService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserLoginRepository userLoginRepository;

    @Override
    public UserLoginEntity login(UserLoginDTO userLoginDTO) {

        return userLoginRepository.findOne(Example.of(
                new UserLoginEntity()
                        .setIdentifier(userLoginDTO.getIdentifier())
                        .setCredential(userLoginDTO.getCredential())
        )).orElse(null);
    }

    @Override
    @Transactional
    public UserEntity register(UserRegisterDTO userRegisterDTO) {


        UserEntity userEntity = userRepository.save(new UserEntity());

        userLoginRepository.save(
                new UserLoginEntity()
                    .setUid(userEntity.getId())
                    .setIdentifier(userRegisterDTO.getNickname())
                    .setCredential(userRegisterDTO.getPassword())
        );

        userRepository.flush();
        userLoginRepository.flush();

        return userEntity;
    }

    @Override
    public boolean logout() {
        return false;
    }
}