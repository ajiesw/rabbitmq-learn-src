package com.kuer.study.consumer.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author kuer
 */
@Service
@RabbitListener(queues = {"email.fanout.queue"})
public class EmailService {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("email message is:" + message);
    }
}
