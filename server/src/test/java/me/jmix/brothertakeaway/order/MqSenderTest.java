package me.jmix.brothertakeaway.order;

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
    void send() {
        System.out.println("--- test begin");
        amqpTemplate.convertAndSend("myQueue", "now: " + new Date());
    }

    @Test
    void sendOrder() {
        amqpTemplate.convertAndSend("myOrder", "computer", "now: " + new Date());
    }
}