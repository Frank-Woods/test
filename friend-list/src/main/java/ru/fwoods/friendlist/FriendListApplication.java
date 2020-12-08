package ru.fwoods.friendlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FriendListApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendListApplication.class, args);
    }

}
