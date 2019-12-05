package cn.falleaves.auth;

import cn.falleaves.auth.model.UserLogin;
import cn.falleaves.auth.model.dto.UserLoginDTO;
import cn.falleaves.auth.repository.UserLoginRepository;
import cn.falleaves.auth.service.impl.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthApplicationTests {

    @Autowired
    public AuthService authService;

    @Test
    void contextLoads() throws Exception {
        authService.login(new UserLoginDTO("graceyu", "123456"));
    }

}
