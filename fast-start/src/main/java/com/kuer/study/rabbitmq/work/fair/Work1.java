package com.kuer.study.rabbitmq.work.fair;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author kuer
 */
public class Work1 {

    public static void main(String[] args){
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
                final Channel finalChannel = channel;
                // 设置Qos，公平模式必需设置
                finalChannel.basicQos(1);
                channel.basicConsume(queueName, false, new DeliverCallback() {
                    public void handle(String consumerTag, Delivery message) throws IOException {
                        System.out.println(queueName + "1接收到：" + new String(message.getBody(), "UTF-8"));
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // 手动应答ack
                        finalChannel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                    }
                }, new CancelCallback() {
                    public void handle(String consumerTag) throws IOException {
                        System.out.println("接收消息失败");
                    }
                });
                System.out.println("Consumer start");
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 关闭通道
                if (channel != null && channel.isOpen()){
                    try {
                        channel.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 关闭连接
                if (connection != null && connection.isOpen()){
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
