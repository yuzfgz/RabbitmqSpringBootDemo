package com.demo01.cc;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer2 {
    // 定义队列名称
    private final static String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、建立工厂连接
        Connection connection = ConnectionFactoryUtil.getConnection();

        // 2、创建信道
        Channel channel = connection.createChannel();

        // 3、创建队列、并持久化
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 4、发送大量消息
        /**
         * 参数3：表示该消息为持久化消息，即除了保存到内存还会保存到磁盘中
         */
        for (int i = 0; i < 30; i++) {
            channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,
                    ("你好，你有新快递编号为："+i).getBytes());
        }

        // 6、关闭资源
        channel.close();
        connection.close();
    }
}
