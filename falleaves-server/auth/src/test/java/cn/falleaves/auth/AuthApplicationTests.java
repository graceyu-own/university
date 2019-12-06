package cn.falleaves.auth;

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
    }

}
