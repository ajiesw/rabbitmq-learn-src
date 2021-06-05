package com.kuer.study.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 设置队列过期时间
 * @author kuer
 */
@Configuration
public class RabbitMqTTLConfig {

    private String exchangeName = "TTL_direct_exchange";
    /**
     * 注册direct模式的交换机
     */
    @Bean
    public DirectExchange ttlExchange(){
        return new DirectExchange(exchangeName, true, false);
    }

    /**
     *注册队列,设置队列过期时间
     */
    @Bean
    public Queue ttlDirectQueue(){
        // 设置过期时间
        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 5000);
        return new Queue("ttl.direct.queue", true, false, false, map);
    }

    /**
     * 绑定关系
     */
    @Bean
    public Binding ttlDirectBinding(){
        return BindingBuilder.bind(ttlDirectQueue()).to(ttlExchange()).with("ttl");
    }
}
