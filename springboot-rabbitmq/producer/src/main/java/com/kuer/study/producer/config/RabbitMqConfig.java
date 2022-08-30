package com.kuer.study.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kuer
 */
@Configuration
public class RabbitMqConfig {

    private String exchangeName = "fanout_order_exchange";

    /**
     * 注册fanout模式的交换机
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(exchangeName, true, false);
    }

    /**
     * 注册队列
     */
    @Bean
    public Queue smsQueue() {
        return new Queue("sms.fanout.queue", true, false, false);
    }

    /**
     * 注册队列
     */
    @Bean
    public Queue weiQueue() {
        return new Queue("wei.fanout.queue", true, false, false);
    }

    /**
     * 注册队列
     */
    @Bean
    public Queue emailQueue() {
        return new Queue("email.fanout.queue", true, false, false);
    }

    /**
     * 绑定关系
     */
    @Bean
    public Binding smsBinding() {
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    /**
     * 绑定关系
     */
    @Bean
    public Binding weiBinding() {
        return BindingBuilder.bind(weiQueue()).to(fanoutExchange());
    }

    /**
     * 绑定关系
     */
    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
}
