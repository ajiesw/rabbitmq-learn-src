package com.kuer.study.producer.service;

import com.alibaba.fastjson.JSON;
import com.kuer.study.producer.pojo.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kuer
 */
@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 广播信息
     * @param order
     */
    public void pushMsg(Order order){

        String orderStr = JSON.toJSONString(order);
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderStr);
    }

    /**
     * 多播
     * @param order
     */
    public void pushMsgWithDirect(Order order){

        String orderStr = JSON.toJSONString(order);
        String exchangeName = "direct_order_exchange";
        String routingKey = "network";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderStr);
    }

    /**
     * 匹配组播
     * @param order
     */
    public void pushMsgWithTopic(Order order){

        String orderStr = JSON.toJSONString(order);
        String exchangeName = "topic_order_exchange";
        String routingKey = "sms.wei";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderStr);
    }
}
