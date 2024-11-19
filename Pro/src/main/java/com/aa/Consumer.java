package com.demo01.aa;
import com.demo01.cc.ConnectionFactoryUtil;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、建立工厂连接
        Connection connection = ConnectionFactoryUtil.getConnection();

        // 2、建立信道
        Channel channel1 = connection.createChannel();
        // 3、监听队列
        channel1.basicConsume("SEND_EMAIL2", true, new DefaultConsumer(channel1) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("SEND_EMAIL2 接收消息："+message);
            }
        });

        // 2、建立信道
        Channel channel2 = connection.createChannel();
        // 3、监听队列
        channel2.basicConsume("SEND_MESSAGE2", true, new DefaultConsumer(channel2) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("SEND_MESSAGE2 接收消息："+message);
            }
        });

        // 2、建立信道
        Channel channel3 = connection.createChannel();
        // 3、监听队列
        channel3.basicConsume("SEND_STATION2", true, new DefaultConsumer(channel3) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("SEND_STATION2 接收消息："+message);
            }
        });
    }
}
