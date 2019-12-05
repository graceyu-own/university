package cn.falleaves.auth.service.impl;

import cn.falleaves.auth.model.UserInfo;
import cn.falleaves.auth.model.UserLogin;
import cn.falleaves.auth.model.dto.UserLoginDTO;
import cn.falleaves.auth.model.dto.UserRegisterDTO;
import cn.falleaves.auth.repository.UserInfoRepository;
import cn.falleaves.auth.repository.UserLoginRepository;
import cn.falleaves.auth.service.IAuthService;
import cn.falleaves.common.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean login(UserLoginDTO userLoginDTO) {

        UserLogin userLogin = new UserLogin()
                .setIdentifier(userLoginDTO.getIdentifier())
                .setCredential(userLoginDTO.getCredential());

        Optional<UserLogin> one = userLoginRepository.findOne(Example.of(userLogin));
        return one.isPresent();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(UserRegisterDTO userRegisterDTO) {

        UserInfo userInfo = new UserInfo()
                .setNickname(userRegisterDTO.getNickname())
                .setEmail(userRegisterDTO.getEmail());

        userInfo = userInfoRepository.save(userInfo);

        UserLogin userLogin = new UserLogin()
                .setUid(userInfo.getId())
                .setTypes(IdentifierType.NICKNAME.getTypeId())
                .setIdentifier(userRegisterDTO.getNickname())
                .setCredential(userRegisterDTO.getPassword());

        userLoginRepository.save(userLogin);

        userLogin
                .setTypes(IdentifierType.EMAIL.getTypeId())
                .setIdentifier(userRegisterDTO.getEmail());

        userLoginRepository.save(userLogin);
        userLoginRepository.flush();

        return false;
    }

    @Override
    public boolean logout() throws Exception {
        return false;
    }
}
