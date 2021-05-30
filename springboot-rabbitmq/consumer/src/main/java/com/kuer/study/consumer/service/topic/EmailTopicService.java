package com.kuer.study.consumer.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/**
 * 注解绑定
 * @author kuer
 */
@Service
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "email.topic.queue", durable = "true", autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange", type = ExchangeTypes.TOPIC),
        key = "#.email.#"
))
public class EmailTopicService {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("email message is:" + message);
    }
}
