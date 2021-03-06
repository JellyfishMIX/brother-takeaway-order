package me.jmix.brothertakeaway.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.product.client.ProductClient;
import me.jmix.brothertakeaway.product.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author JellyfishMIX
 * @date 2020/6/5 10:50 下午
 */
@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {

    // 2. 第二种方式，使用LoadBalancerClient
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private ProductClient productClient;

    @GetMapping("/get-product-message")
    public String getProductMessage() {
        // // 1. 第一种方式(直接使用restTemplate)
        // RestTemplate restTemplate = new RestTemplate();
        // String response = restTemplate.getForObject("http://localhost:8080/brother-takeaway-product/server/message", String.class);

        // 2. 第二种方式(利用loadBalancerClient通过应用名获取url，然后使用restTemplate)
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("brother-takeaway-product");
        String url = String.format("http://%s:%s/server/message", serviceInstance.getHost(), serviceInstance.getPort());
        String response = restTemplate.getForObject(url, String.class);

        // // 3. 第三种方式(利用@LoadBalanced，可在restTemplate里使用应用名字)
        // String response = restTemplate.getForObject("http://brother-takeaway-product/brother-takeaway-product/server/message", String.class);
        return response;
    }

    @GetMapping("/query-product-message-by-feign")
    public String queryProductMessageByFeign() {
        String response = productClient.productMsg();
        return response;
    }

    @GetMapping("/list-for-product")
    public String listForProduct() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(productClient.listForProduct(Arrays.asList("123456", "123458")));
    }

    @GetMapping("/decrease-stock")
    public String decreaseStock() {
        CartVO cartVO1 = new CartVO("123456", 2);
        CartVO cartVO2 = new CartVO("123457", 2);
        productClient.decreaseStock(Arrays.asList(cartVO1, cartVO2));
        return "ok";
    }

}
