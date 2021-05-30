package com.kuer.study.consumer.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author kuer
 */
@Service
@RabbitListener(queues = {"sms.fanout.queue"})
public class SmsService {
    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("sms message is:" + message);
    }
}
