package org.techas.falleaves.services.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;
import org.techas.falleaves.dao.UserRepository;
import org.techas.falleaves.model.UserEntity;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAuthApplication {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void contextLoads() {

    }

}
