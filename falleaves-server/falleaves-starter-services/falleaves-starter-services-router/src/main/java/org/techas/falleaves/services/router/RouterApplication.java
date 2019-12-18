package org.techas.falleaves.services.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableZuulProxy
@ComponentScan(basePackages = "org.techas.falleaves.services.router.conf")
public class RouterApplication {

    public static void main(String[] params) {
        SpringApplication.run(RouterApplication.class, params);
    }

}
