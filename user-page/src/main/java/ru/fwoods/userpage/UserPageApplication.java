package ru.fwoods.userpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserPageApplication.class, args);
    }

}
