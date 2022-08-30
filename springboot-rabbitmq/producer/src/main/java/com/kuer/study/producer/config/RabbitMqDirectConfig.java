package com.kuer.study.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kuer
 */
@Configuration
public class RabbitMqDirectConfig {

    private String exchangeName = "direct_order_exchange";

    /**
     * 注册direct模式的交换机
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName, true, false);
    }

    /**
     * 注册队列
     */
    @Bean
    public Queue smsDirectQueue() {
        return new Queue("sms.direct.queue", true, false, false);
    }

    /**
     * 注册队列
     */
    @Bean
    public Queue weiDirectQueue() {
        return new Queue("wei.direct.queue", true, false, false);
    }

    /**
     * 注册队列
     */
    @Bean
    public Queue emailDirectQueue() {
        return new Queue("email.direct.queue", true, false, false);
    }

    /**
     * 绑定关系
     */
    @Bean
    public Binding smsDirectBinding() {
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with("money");
    }

    /**
     * 绑定关系
     */
    @Bean
    public Binding weiDirectBinding() {
        return BindingBuilder.bind(weiDirectQueue()).to(directExchange()).with("network");
    }

    /**
     * 绑定关系
     */
    @Bean
    public Binding emailDirectBinding() {
        return BindingBuilder.bind(emailDirectQueue()).to(directExchange()).with("network");
    }
}
