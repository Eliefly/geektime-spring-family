package geektime.spring.springbucks.waiter.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {

    // 消息的监听消费
    @StreamListener(Barista.FINISHED_ORDERS)
    public void listenFinishedOrders(Long id) {
        // 简单打印一下
        log.info("We've finished an order [{}].", id);
    }
}
