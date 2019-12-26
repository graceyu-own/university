package org.techas.falleaves.security.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.techas.falleaves.dao.UserLoginRepository;
import org.techas.falleaves.dao.UserRepository;
import org.techas.falleaves.model.UserEntity;
import org.techas.falleaves.model.UserLoginEntity;
import org.techas.falleaves.utils.UserStatusType;

import java.util.Optional;

public class BasicRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

        String identifier = (String) token.getPrincipal();
        String credential = new String((char[]) token.getCredentials());;

        Optional<UserLoginEntity> one = userLoginRepository.findOne(Example.of(new UserLoginEntity().setIdentifier(identifier).setCredential(credential)));
        UserLoginEntity userLoginEntity = one.orElse(null);

        if (null == userLoginEntity) {
            throw new UnknownAccountException("Unknown account");
        }

        if (!credential.equals(userLoginEntity.getCredential())) {
            throw new IncorrectCredentialsException("Incorrect credentials");
        }

        Optional<UserEntity> one1 = userRepository.findOne(Example.of(new UserEntity().setId(userLoginEntity.getUid())));
        UserEntity userEntity = one1.orElse(null);

        if (null == userEntity || userEntity.getStatus() == UserStatusType.LOCKED.getStatusType()) {
            throw new LockedAccountException("Incorrect credentials");
        }

        return new SimpleAuthenticationInfo(userEntity, credential, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();


        return simpleAuthorizationInfo;
    }
}