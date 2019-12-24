package org.techas.falleaves.services.auth.service.impl;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.techas.falleaves.cache.service.ICacheService;
import org.techas.falleaves.dao.UserInfoRepository;
import org.techas.falleaves.dao.UserLoginRepository;
import org.techas.falleaves.dao.UserRepository;
import org.techas.falleaves.model.UserEntity;
import org.techas.falleaves.model.UserInfoEntity;
import org.techas.falleaves.model.UserLoginEntity;
import org.techas.falleaves.services.auth.service.IAuthService;
import org.techas.falleaves.utils.Attr;
import org.techas.falleaves.utils.DesUtils;
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
    public UserLoginEntity login(String identifier, String credential) {

        return userLoginRepository.findOne(Example.of(
                new UserLoginEntity().setIdentifier(identifier).setCredential(credential)
        )).orElse(null);
    }

    @Override
    @Transactional
    public UserEntity register(String nickname, String email, String password) {

        UserEntity userEntity = userRepository.save(new UserEntity().setStatus(UserStatusType.NORMAL.getStatusType()));

        userLoginRepository.save(
                new UserLoginEntity()
                    .setUid(userEntity.getId())
                    .setIdentifier(nickname)
                    .setCredential(password)
        );

        userInfoRepository.save(
                new UserInfoEntity()
                    .setUid(userEntity.getId())
                    .setNickname(nickname)
                    .setEmail(email)
        );

        userRepository.flush();
        userLoginRepository.flush();
        userInfoRepository.flush();

        return userEntity;
    }


    @Override
    public boolean sendRegisterMail(String email) {
        String code = Utils.getRandomString(6).toUpperCase();
        System.out.println("本次验证码为: " + code);
        return simpleRedisCacheService.set(Attr.REDIS_AUTH_REGISTER_MAIL_PREFIX + email, code, Attr.REDIS_AUTH_REGISTER_MAIL_EXPIRE);
    }

    @Override
    public String getRegisterMail(String email) {
        if(simpleRedisCacheService.has(Attr.REDIS_AUTH_REGISTER_MAIL_PREFIX + email)) {
            return simpleRedisCacheService.get(Attr.REDIS_AUTH_REGISTER_MAIL_PREFIX + email);
        }
        return null;
    }

    @Override
    public void deleteRegisterMail(String email) {
        if(simpleRedisCacheService.has(Attr.REDIS_AUTH_REGISTER_MAIL_PREFIX + email)) {
            simpleRedisCacheService.delete(Attr.REDIS_AUTH_REGISTER_MAIL_PREFIX + email);
        }
    }

    // http://localhost:8086/#/auth/set-password?
    // email = ?
    // emailValid = ?
    @Override
    public boolean sendResetPasswordMail(String email) {
        String code = Utils.getRandomString(32).toUpperCase();

        String data = "email=" + email + "&emailValid=" + code;

        simpleRedisCacheService.set(Attr.REDIS_AUTH_RESETPASSWORD_MAIL_PREFIX + email, code, Attr.REDIS_AUTH_RESETPASSWORD_MAIL_EXPIRE);
        System.out.println("本次URL为: http://localhost:8086/#/auth/set-password?"+ DesUtils.encodeBase64(data));
        return true;
    }

    @Override
    public String getResetPasswordMail(String email) {
        if(simpleRedisCacheService.has(Attr.REDIS_AUTH_RESETPASSWORD_MAIL_PREFIX + email)) {
            return simpleRedisCacheService.get(Attr.REDIS_AUTH_RESETPASSWORD_MAIL_PREFIX  + email);
        }
        return null;
    }

    @Override
    public void deleteResetPasswordMail(String email) {
        if(simpleRedisCacheService.has(Attr.REDIS_AUTH_RESETPASSWORD_MAIL_PREFIX + email)) {
            simpleRedisCacheService.delete(Attr.REDIS_AUTH_RESETPASSWORD_MAIL_PREFIX  + email);
        }
    }

    @Override
    public void setPassword(String email, String password) {



    }
}