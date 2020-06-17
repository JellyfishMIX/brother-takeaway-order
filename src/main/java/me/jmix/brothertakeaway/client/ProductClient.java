package me.jmix.brothertakeaway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author JellyfishMIX
 * @date 2020/6/17 4:44 下午
 */
// name是应用的名字，表示要访问哪个应用的API
@FeignClient(name = "brother-takeaway-product")
public interface ProductClient {
    @GetMapping("/brother-takeaway-product/server/message")
    String productMsg();
}
