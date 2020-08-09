package me.jmix.brothertakeaway.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author JellyfishMIX
 * @date 2020/7/26 22:38
 */
@RequestMapping("/hystrix")
@RestController
@DefaultProperties
public class HystrixController {
    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/product-list")
    public String getProductInfoList() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8082/product/list", String.class);
    }

    @HystrixCommand(commandProperties = {
            // 超时时间
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @GetMapping("/product-list2")
    public String getProductInfoList2() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8082/product/list", String.class);
    }

    @HystrixCommand(commandProperties = {
            // 开启熔断
            @HystrixProperty(name = "circuitBreaker.enable", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "true"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "true"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "true")
    })
    @GetMapping("/product-list3")
    public String getProductInfoList3() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8082/product/list", String.class);
    }

    private String fallback() {
        return "太拥挤了，请稍后再试";
    }

    private String defaultFallback() {
        return "默认提示：太拥挤了，请稍后再试";
    }
}
