package org.techas.falleaves.services.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.techas.falleaves.cache.service.ICacheService;
import org.techas.falleaves.dao.UserInfoRepository;
import org.techas.falleaves.dao.UserLoginRepository;
import org.techas.falleaves.dao.UserRepository;
import org.techas.falleaves.model.UserEntity;
import org.techas.falleaves.model.UserInfoEntity;
import org.techas.falleaves.model.UserLoginEntity;
import org.techas.falleaves.model.dto.EmailDTO;
import org.techas.falleaves.model.dto.UserLoginDTO;
import org.techas.falleaves.model.dto.UserRegisterDTO;
import org.techas.falleaves.model.vo.EmailVO;
import org.techas.falleaves.services.auth.service.IAuthService;
import org.techas.falleaves.utils.Attr;
import org.techas.falleaves.utils.UserStatusType;
import org.techas.falleaves.utils.Utils;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class AuthService implements IAuthService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserLoginRepository userLoginRepository;

    @Resource
    private UserInfoRepository userInfoRepository;

    @Resource
    private ICacheService<String, String> simpleRedisCacheService;

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

        UserEntity userEntity = userRepository.save(new UserEntity().setStatus(UserStatusType.NORMAL.getStatusType()));

        userLoginRepository.save(
                new UserLoginEntity()
                    .setUid(userEntity.getId())
                    .setIdentifier(userRegisterDTO.getNickname())
                    .setCredential(userRegisterDTO.getPassword())
        );

        userInfoRepository.save(
                new UserInfoEntity()
                    .setUid(userEntity.getId())
                    .setNickname(userRegisterDTO.getNickname())
                    .setEmail(userRegisterDTO.getEmail())
        );

        userRepository.flush();
        userLoginRepository.flush();
        userInfoRepository.flush();

        return userEntity;
    }


    @Override
    public boolean sendRegisterMail(EmailDTO emailDTO) {
        String code = Utils.getRandomString(6).toUpperCase();
        System.out.println("本次验证码为: " + code);
        return simpleRedisCacheService.set(Attr.REDIS_AUTH_REGISTER_MAIL_PREFIX + emailDTO.getEmail(), code, Attr.REDIS_AUTH_REGISTER_MAIL_EXPIRE);
    }

    @Override
    public String getRegisterMail(EmailDTO emailDTO) {
        if(simpleRedisCacheService.has(Attr.REDIS_AUTH_REGISTER_MAIL_PREFIX + emailDTO.getEmail())) {
            return simpleRedisCacheService.get(Attr.REDIS_AUTH_REGISTER_MAIL_PREFIX + emailDTO.getEmail());
        }
        return null;
    }
}