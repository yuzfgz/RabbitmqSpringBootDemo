package com.demo01.cc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer1 {
    // 定义队列名称
    private final static String QUEUE_NAME = "qu1";
    public static void main(String[] args) throws IOException, TimeoutException {

        // 1、建立连接工厂
        Connection connection = ConnectionFactoryUtil.getConnection();

        // 2、建立信道
        Channel channel = connection.createChannel();

        // 3、监听队列
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                /*
                回调方法：当收到消息时，会自动执行该方法
                consumerTag: 标识
                envelope: 获取一些信息，如：交换机、路由key
                properties: 配置信息
                */
                System.out.println("consumerTag:"+consumerTag);
                System.out.println("envelope:"+envelope);
                System.out.println("RoutingKey:"+envelope.getRoutingKey());
                System.out.println("properties:"+properties);
                System.out.println("接受消息为：" + message);
            }
        };

        /**
         * 参数1：监听的队列名
         * 参数2：是否自动签收，如果设置为false，则需要手动确认消息已收到，否则MQ会一直发送消息
         * 参数3：Consumer的实现类，重写该类方法表示接受到消息后如何消费
         */
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
