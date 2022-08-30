package com.kuer.study.rabbitmq.work.polling;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kuer
 */
public class Work2 {

    public static void main(String[] args) {
        new Thread(runnable, "work1").start();
    }

    private static Runnable runnable = new Runnable() {
        public void run() {
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
                // 获取队列名称
                final String queueName = Thread.currentThread().getName();
                // 获取消息
                channel.basicConsume(queueName, true, new DeliverCallback() {
                    public void handle(String consumerTag, Delivery message) throws IOException {
                        System.out.println(queueName + "2接收到：" + new String(message.getBody(), "UTF-8"));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, new CancelCallback() {
                    public void handle(String consumerTag) throws IOException {
                        System.out.println("接收消息失败");
                    }
                });
                System.out.println("Consumer start");
                System.in.read();
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
    };
}
