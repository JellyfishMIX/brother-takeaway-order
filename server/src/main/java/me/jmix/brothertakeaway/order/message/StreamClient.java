package me.jmix.brothertakeaway.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author JellyfishMIX
 * @date 2020/6/27 11:14 下午
 */
public interface StreamClient {
    String EXCHANGE = "myMessage";
    String EXCHANGE_FEEDBACK = "feedback";

    // myMessage是绑定的Exchange名称。启动后自动创建一个Queue。
    @Input(StreamClient.EXCHANGE)
    SubscribableChannel input();

    // myMessage是绑定的Exchange名称。启动后自动创建一个Queue。
    @Output(StreamClient.EXCHANGE)
    MessageChannel output();

    // feedback是绑定的Exchange名称。启动后自动创建一个Queue。
    @Input(StreamClient.EXCHANGE_FEEDBACK)
    SubscribableChannel feedbackInput();

    // feedback是绑定的Exchange名称。启动后自动创建一个Queue。
    @Output(StreamClient.EXCHANGE_FEEDBACK)
    MessageChannel feedbackOutput();
}
