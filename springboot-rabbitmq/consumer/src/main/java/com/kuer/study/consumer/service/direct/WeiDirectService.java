package com.kuer.study.consumer.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author kuer
 */
@Service
@RabbitListener(queues = {"wei.direct.queue"})
public class WeiDirectService {
    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("wei message is:" + message);
    }
}
