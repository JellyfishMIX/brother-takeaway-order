package me.jmix.brothertakeaway.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收mq消息
 *
 * @author JellyfishMIX
 * @date 2020/6/26 8:51 下午
 */
@Slf4j
@Component
public class MqReceiver {
    // 1. 手动创建mq
    // @RabbitListener(queues = "myQueue")
    // 2. 自动创建mq
    // @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    // 3. 自动创建队列，Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myExchange"),
            value = @Queue("myQueue")
    ))
    public void process(String message) {
        log.info("computer MqReceiver: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            // RoutingKey
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void processComputer(String message) {
        log.info("computer MqReceiver: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            // RoutingKey
            key = "fruit",
            value = @Queue("fruitOrder")
    ))
    public void processFruit(String message) {
        log.info("fruit MqReceiver: {}", message);
    }
}
