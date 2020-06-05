package me.jmix.brothertakeaway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BrotherTakeawayOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrotherTakeawayOrderApplication.class, args);
    }

}
