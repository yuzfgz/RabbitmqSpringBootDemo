package com.demo01.bb;

import com.demo01.cc.ConnectionFactoryUtil;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class ConsumerMessage {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、建立工厂连接
        Connection connection = ConnectionFactoryUtil.getConnection();

        // 2、建立信道
        Channel channel = connection.createChannel();
        // 3、监听队列
        channel.basicConsume("SEND_MESSAGE", true, new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("接收短信消息："+message);
            }
        });
    }
}
