package org.techas.falleaves.security.service.impl;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.techas.falleaves.cache.service.ICacheService;
import org.techas.falleaves.dao.UserInfoRepository;
import org.techas.falleaves.dao.UserRepository;
import org.techas.falleaves.model.UserEntity;
import org.techas.falleaves.model.UserInfoEntity;
import org.techas.falleaves.security.service.ISecurityService;
import org.techas.falleaves.utils.Attr;
import org.techas.falleaves.utils.Utils;

import javax.annotation.Resource;

@Service
public class SecurityService implements ISecurityService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserInfoRepository userInfoRepository;

    @Resource
    private ICacheService<String, String> simpleRedisCacheService;


    @Override
    public boolean hasUserById(Long id) {
        return userRepository.exists(Example.of(new UserEntity().setId(id)));
    }

    @Override
    public boolean hasNickname(String nickname) {

        /*return null != userInfoRepository.findOne(
            Example.of(
                new UserInfoEntity()
                    .setNickname(nickname)
            )
        ).orElse(null);*/

        return userInfoRepository.exists(Example.of(new UserInfoEntity().setNickname(nickname)));
    }

    @Override
    public boolean hasEmail(String email) {

        /*return null != userInfoRepository.findOne(
            Example.of(
                new UserInfoEntity()
                    .setEmail(email)
            )
        ).orElse(null);*/

        return userInfoRepository.exists(Example.of(new UserInfoEntity().setEmail(email)));
    }

    @Override
    public boolean setLastSendMailTimeBySession(String sessionId) {
        return simpleRedisCacheService.set(Attr.REDIS_LAST_SEND_MAIL_TIME_SESSION_PREFIX + sessionId, Utils.getCurrentTimestamp() + "", Attr.REDIS_LAST_SEND_MAIL_TIME_SESSION_EXPIRE);
    }

    @Override
    public boolean setLastSendMailTimeByEmail(String email) {
        return simpleRedisCacheService.set(Attr.REDIS_LAST_SEND_MAIL_TIME_EMAIL_PREFIX + email, Utils.getCurrentTimestamp() + "", Attr.REDIS_LAST_SEND_MAIL_TIME_EMAIL_EXPIRE);
    }

    @Override
    public boolean isFreqMailBySession(String sessionId) {
        if(simpleRedisCacheService.has(Attr.REDIS_LAST_SEND_MAIL_TIME_SESSION_PREFIX + sessionId)){
            Long time = Long.parseLong(simpleRedisCacheService.get(Attr.REDIS_LAST_SEND_MAIL_TIME_SESSION_PREFIX + sessionId));
            return Utils.getCurrentTimestamp() - time < Attr.SEND_MAIL_COUNTDOWN;
        }

        return false;
    }

    @Override
    public boolean isFreqMailByEmail(String email) {
        if(simpleRedisCacheService.has(Attr.REDIS_LAST_SEND_MAIL_TIME_EMAIL_PREFIX + email)){
            Long time = Long.parseLong(simpleRedisCacheService.get(Attr.REDIS_LAST_SEND_MAIL_TIME_EMAIL_PREFIX + email));
            return Utils.getCurrentTimestamp() - time < Attr.SEND_MAIL_COUNTDOWN;
        }

        return false;
    }

    @Override
    public UserInfoEntity findUserByEmail(String email) {
        return userInfoRepository.findOne(Example.of(new UserInfoEntity().setEmail(email))).orElse(null);
    }

}
