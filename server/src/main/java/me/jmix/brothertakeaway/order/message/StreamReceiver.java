package me.jmix.brothertakeaway.order.message;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.order.dto.OrderDTO;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author JellyfishMIX
 * @date 2020/6/27 11:17 下午
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {
    // @SteamListener默认状态下，不能有多个方法绑定同一个Channel，这样Channel会无法判断把Message送往哪个方法（初学时写下此注释，此说法存疑）

    // /**
    //  * 监听字符串信息
    //  *
    //  * @param message message
    //  */
    // @StreamListener(value = StreamClient.EXCHANGE)
    // public void processString(Object message) {
    //     log.info("StreamReceiver: {}", message);
    // }

    /**
     * 监听OrderDTO信息
     *
     * @param message message
     * @return
     */
    @StreamListener(value = StreamClient.EXCHANGE)
    @SendTo(StreamClient.EXCHANGE_FEEDBACK)
    public String processObject(OrderDTO message) {
        log.info("StreamReceiver: {}", message);
        return "received";
    }

    /**
     * 监听反馈
     *
     * @param message message
     */
    @StreamListener(value = StreamClient.EXCHANGE_FEEDBACK)
    public void processResponse(String message) {
        log.info("Feedback: " + message);
    }
}
