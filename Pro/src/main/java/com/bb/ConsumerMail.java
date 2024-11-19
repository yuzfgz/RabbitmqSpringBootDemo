package com.demo01.bb;

import com.demo01.cc.ConnectionFactoryUtil;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;



public class ConsumerMail {


    private static final String SEND_MESSAGE = "SEND_MESSAGE";
    private static final String SEND_MAIL = "SEND_MAIL";
    private static final String SEND_STATION = "SEND_STATION";

    private static List<String> qunames = List.of(SEND_MAIL, SEND_STATION, SEND_MESSAGE);

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、建立工厂连接
        Connection connection = ConnectionFactoryUtil.getConnection();

        for (String quname : qunames) {
            // 2、建立信道
            Channel channel = connection.createChannel();
            // 3、监听队列
            channel.basicConsume(quname, true, new DefaultConsumer(channel) {

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body,"UTF-8");
                    System.out.println(quname+"接收："+message);
                }
            });
        }
    }
}
