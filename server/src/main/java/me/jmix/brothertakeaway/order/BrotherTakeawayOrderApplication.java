package me.jmix.brothertakeaway.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "me.jmix.brothertakeaway.product.client")
// @SpringBootApplication
// @EnableDiscoveryClient
// @EnableCircuitBreaker
// 以上三个注解已包含在@SpringCloudApplication中
@SpringCloudApplication
public class BrotherTakeawayOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrotherTakeawayOrderApplication.class, args);
    }

}
