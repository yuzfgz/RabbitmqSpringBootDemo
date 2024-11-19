package com.demo01.cc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer3 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、建立连接工厂
        Connection connection = ConnectionFactoryUtil.getConnection();

        // 2、建立信道
        Channel channel = connection.createChannel();

        // 3、监听队列处理消息
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("消费者3 消费消息：" + message);
            }
        };

        channel.basicConsume("work_queue", true, consumer);
    }
}
