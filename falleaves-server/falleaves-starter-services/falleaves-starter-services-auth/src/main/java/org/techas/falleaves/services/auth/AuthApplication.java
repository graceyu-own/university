package org.techas.falleaves.services.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.techas.falleaves.dao")
@EntityScan(basePackages = "org.techas.falleaves.model")
//@ComponentScan(basePackages = "org.techas.falleaves.security.conf")
public class AuthApplication {

    public static void main(String[] params) {
        SpringApplication.run(AuthApplication.class, params);
    }
}