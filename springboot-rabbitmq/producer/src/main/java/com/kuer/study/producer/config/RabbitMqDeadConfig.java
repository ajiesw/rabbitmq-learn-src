package com.kuer.study.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置死信交换机
 * @author kuer
 */
@Configuration
public class RabbitMqDeadConfig {

    private String exchangeName = "dead_direct_exchange";
    /**
     * 注册direct模式的交换机
     */
    @Bean
    public DirectExchange deadExchange(){
        return new DirectExchange(exchangeName, true, false);
    }

    /**
     *注册队列,设置队列过期时间
     */
    @Bean
    public Queue deadDirectQueue(){
        return new Queue("dead.queue", true, false, false);
    }

    /**
     * 绑定关系
     */
    @Bean
    public Binding deadDirectBinding(){
        return BindingBuilder.bind(deadDirectQueue()).to(deadExchange()).with("dead");
    }
}
