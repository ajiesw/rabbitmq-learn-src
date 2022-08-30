package com.kuer.study.consumer.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author kuer
 */
@Service
@RabbitListener(queues = {"sms.direct.queue"})
public class SmsDirectService {
    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("sms message is:" + message);
    }
}
