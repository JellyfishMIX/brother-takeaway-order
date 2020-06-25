package me.jmix.brothertakeaway.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Cloud Config 演示
 *
 * @author JellyfishMIX
 * @date 2020/6/25 10:15 上午
 */
@RestController
@RequestMapping("/env")
@RefreshScope
public class EnvController {
    @Value("${env}")
    private String env;

    @GetMapping("/print")
    public String print() {
        return env;
    }
}
