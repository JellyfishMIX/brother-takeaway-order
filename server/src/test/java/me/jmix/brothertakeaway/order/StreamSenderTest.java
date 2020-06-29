package me.jmix.brothertakeaway.order;

import me.jmix.brothertakeaway.order.dto.OrderDTO;
import me.jmix.brothertakeaway.order.message.StreamClient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Spring Cloud Stream测试
 *
 * @author JellyfishMIX
 * @date 2020/6/27 11:21 下午
 */
public class StreamSenderTest extends BrotherTakeawayOrderApplicationTests {
    @Resource
    private StreamClient streamClient;

    @Test
    // @Disabled
    void sendMessage() {
        String message = "now: " + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }

    @Test
    // @Disabled
    void sendObject() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123456");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
