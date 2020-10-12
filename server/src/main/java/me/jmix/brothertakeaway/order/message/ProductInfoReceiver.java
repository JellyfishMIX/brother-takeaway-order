package me.jmix.brothertakeaway.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.order.utils.JsonUtil;
import me.jmix.brothertakeaway.product.entity.ProductInfo;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JellyfishMIX
 * @date 2020/7/9 00:04
 */
@Component
@Slf4j
public class ProductInfoReceiver {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String PRODUCT_STOCK_TEMPLATE = "product-stock-%s";

    /**
     * RabbitListener example
     * Note: If the service is started locally, the redis port of ECS may be closed to the local machine, causing an exception.
     *
     * @param message message from rabbit
     */
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        // message => ProductInfo
        List<ProductInfo> productInfoList = (List<ProductInfo>) JsonUtil.fromJson(message, new TypeReference<List<ProductInfo>>() {});
        log.info("从队列【{}】接收到消息: {}", "productInfoList", productInfoList);

        // Save to redis. Note: If the service is started locally, the redis port of ECS may be closed to the local machine, causing an exception.
        for (ProductInfo productInfo : productInfoList) {
            String key = String.format(PRODUCT_STOCK_TEMPLATE, String.valueOf(productInfo.getProductId()));
            String value = String.valueOf(productInfo.getProductStock());
            stringRedisTemplate.opsForValue().set(key, value);
        }
    }
}
