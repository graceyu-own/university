package org.techas.falleaves.services.manager.services.impl;

import org.springframework.stereotype.Service;
import org.techas.falleaves.dao.UserRepository;
import org.techas.falleaves.model.UserEntity;
import org.techas.falleaves.services.manager.services.IManagerUserService;

import javax.annotation.Resource;

@Service
public class ManagerUserService implements IManagerUserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public boolean delUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    @Override
    public boolean updateUser(Long id, Integer status) {
        try {
            userRepository.save(new UserEntity(id, status));
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }
}
