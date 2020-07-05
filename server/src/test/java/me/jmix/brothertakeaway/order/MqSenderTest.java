package me.jmix.brothertakeaway.order;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 发送Mq消息测试
 *
 * @author JellyfishMIX
 * @date 2020/6/26 9:06 下午
 */
public class MqSenderTest extends BrotherTakeawayOrderApplicationTests {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    @Disabled
    void send() {
        System.out.println("--- test begin");
        // 第一个参数：exchange，第二个参数message
        amqpTemplate.convertAndSend("myQueue", "now: " + new Date());
    }

    @Test
    @Disabled
    void sendOrder() {
        // 第一个参数：exchange，第二个参数：routingKey，第三个参数：message
        amqpTemplate.convertAndSend("myOrder", "computer", "now: " + new Date());
    }
}