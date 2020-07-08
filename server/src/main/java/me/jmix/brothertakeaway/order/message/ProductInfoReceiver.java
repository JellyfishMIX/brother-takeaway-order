package me.jmix.brothertakeaway.order.message;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.order.utils.JsonUtil;
import me.jmix.brothertakeaway.product.entity.ProductInfo;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author JellyfishMIX
 * @date 2020/7/9 00:04
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        // message => ProductInfo
        ProductInfo productInfo = (ProductInfo) JsonUtil.fromJson(message, ProductInfo.class);
        log.info("从队列【{}】接收到消息: {}", "productInfo", productInfo);
    }

}
