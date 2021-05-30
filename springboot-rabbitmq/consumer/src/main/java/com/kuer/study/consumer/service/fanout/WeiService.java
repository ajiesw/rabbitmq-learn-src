package com.kuer.study.consumer.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author kuer
 */
@Service
@RabbitListener(queues = {"wei.fanout.queue"})
public class WeiService {
    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("wei message is:" + message);
    }
}
