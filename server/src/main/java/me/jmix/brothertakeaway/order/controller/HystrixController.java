package me.jmix.brothertakeaway.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * the attributes of the @HystrixCommand can be set in bootstrap.yml
 * @HystrixCommand 中的属性也可以在bootstrap.yml中设置
 *
 * @author JellyfishMIX
 * @date 2020/7/26 22:38
 */
@RequestMapping("/hystrix-api")
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {
    /**
     * You can set a separate timeout for this method in bootstrap.yml
     * 可以在bootstrap.yml中为此方法单独设置一个超时时间
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/product-list")
    public String getProductInfoList() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8082/product/list", String.class);
    }

    /**
     * Only add @HystrixCommand annotation.
     * When the service degradation is triggered,
     * the defaultFallback method specified in the @DefaultProperties annotation of the class of the method is called.
     * 只添加@HystrixCommand注解，触发服务降级时会调用所处类的@DefaultProperties注解中指定的defaultFallback方法
     * @return
     */
    @HystrixCommand
    @GetMapping("/product-list2")
    public String getProductInfoList2() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8082/product/list", String.class);
    }

    @HystrixCommand(commandProperties = {
            // 超时时间
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @GetMapping("/product-list3")
    public String getProductInfoList3() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8082/product/list", String.class);
    }

    @HystrixCommand(commandProperties = {
            // 开启熔断
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 滚动时间窗口中，断路器的进行统计的最小请求数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 休眠时间窗到期时间
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 滚动时间窗口中，断路器打开的错误百分比条件
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    @GetMapping("/product-list4")
    public String getProductInfoList4() {
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
