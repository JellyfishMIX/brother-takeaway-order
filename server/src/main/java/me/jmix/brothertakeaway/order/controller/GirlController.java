package me.jmix.brothertakeaway.order.controller;

import me.jmix.brothertakeaway.order.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JellyfishMIX
 * @date 2020/6/26 2:23 上午
 */
@RestController
public class GirlController {
    @Autowired
    private GirlConfig girlConfig;

    @GetMapping("/girl/print")
    public String print() {
        return "name: " + girlConfig.getName() + "\nage: " + girlConfig.getAge();
    }
}
