package com.kuer.study.rabbitmq.all;

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

            // 准备消息内容
            String msg = "hello, my-direct-exchange msg";

            // 自定义交换机名称
            String exchangeName = "my-direct-exchange";
            // 定义交换机类型
            String exchangeType = "direct";
            channel.exchangeDeclare(exchangeName, exchangeType, true);
            // 自定义队列
            channel.queueDeclare("q4", true, false, false, null);
            channel.queueDeclare("q5", true, false, false, null);
            channel.queueDeclare("q6", true, false, false, null);

            // 绑定交换机和队列
            channel.queueBind("q4", exchangeName, "r4");
            channel.queueBind("q5", exchangeName, "r4");
            channel.queueBind("q5", exchangeName, "r6");

            // 定义路由key
            String routeKey = "r4";

            // 发送消息给队列
            channel.basicPublish(exchangeName, routeKey, null, msg.getBytes());
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
