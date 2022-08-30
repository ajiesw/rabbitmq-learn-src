package com.kuer.study.rabbitmq.work.fair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kuer
 */
public class Producer {

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            // 创建连接工厂
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("192.168.92.130");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("admin");
            connectionFactory.setPassword("admin");
            connectionFactory.setVirtualHost("/");
            // 创建连接connection
            connection = connectionFactory.newConnection("myProducer");
            // 通过连接获取通道Channel
            channel = connection.createChannel();

            // 准备交换机
            String exchangeName = "work-exchange";
            // 定义路由key
            String routeKey = "";

            for (int i = 0; i < 20; i++) {
                // 准备消息内容
                String msg = "hello, fanout msg " + i;
                // 发送消息给队列
                channel.basicPublish(exchangeName, routeKey, null, msg.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            // 关闭通道
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 关闭连接
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
