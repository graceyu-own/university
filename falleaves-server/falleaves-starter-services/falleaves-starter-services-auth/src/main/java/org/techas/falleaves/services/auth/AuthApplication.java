package org.techas.falleaves.services.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.techas.falleaves.dao")
public class AuthApplication {

    public static void main(String[] params) {
        SpringApplication.run(AuthApplication.class, params);
    }
}