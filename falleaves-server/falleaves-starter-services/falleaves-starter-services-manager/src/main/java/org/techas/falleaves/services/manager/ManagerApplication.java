package org.techas.falleaves.services.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.techas.falleaves.dao")
@EntityScan("org.techas.falleaves.model")
@ComponentScan("org.techas.falleaves")
public class ManagerApplication {

    public static void main(String[] params){
        SpringApplication.run(ManagerApplication.class, params);
    }

}
