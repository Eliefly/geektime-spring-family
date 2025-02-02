package geektime.spring.springbucks.waiter.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Barista {
    String NEW_ORDERS = "newOrders";
    String FINISHED_ORDERS = "finishedOrders";

    // 需要监听订阅的消息，exchange
    @Input(value = FINISHED_ORDERS)
    SubscribableChannel finishedOrders();

    @Output
    MessageChannel newOrders();
}
