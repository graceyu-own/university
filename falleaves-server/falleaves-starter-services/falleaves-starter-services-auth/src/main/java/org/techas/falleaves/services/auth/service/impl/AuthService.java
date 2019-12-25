package org.techas.falleaves.services.auth.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
    public void login(String identifier, String credential) {

        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(identifier, credential));
    }

    @Override
    @Transactional
    public boolean register(String nickname, String email, String password) {

        try {
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

            return true;
        } catch (Exception ignore) {
            return false;
        }
    }


    @Override
    @Transactional
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
    @Transactional
    public boolean setPassword(Long id, String password) {
        try {
            userLoginRepository.findOne(Example.of(new UserLoginEntity().setId(id))).ifPresent(
                    userLoginEntity ->
                            userLoginRepository.save(
                                    userLoginEntity.setCredential(password)
                            )
            );
            userLoginRepository.flush();
        } catch (Exception e) {
            System.out.println("AuthService.setPassword出现异常:" + e);
            return false;
        }
        return true;
    }

}