package org.techas.falleaves.services.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude = EurekaClientAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "org.techas.falleaves.dao")
@EntityScan(basePackages = "org.techas.falleaves.model")
@ComponentScan(basePackages = "org.techas.falleaves.security.conf")
@ComponentScan(basePackages = "org.techas.falleaves.services.auth.conf")
//@EnableDiscoveryClient
public class AuthApplication {

    public static void main(String[] params) {
        SpringApplication.run(AuthApplication.class, params);
    }
}