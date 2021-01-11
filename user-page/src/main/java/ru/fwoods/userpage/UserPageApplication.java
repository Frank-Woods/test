package ru.fwoods.userpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("ru.fwoods.entities")
public class UserPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserPageApplication.class, args);
    }

}
