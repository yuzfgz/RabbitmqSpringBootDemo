package com.demo01.cc;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Pro01 {
    // 定义队列名称
    private final static String QUEUE_NAME = "qu1";
    public static void main(String[] args) throws IOException, TimeoutException, TimeoutException {

        // 1、建立工厂连接
        Connection connection = ConnectionFactoryUtil.getConnection();

        // 2、建立信道
        Channel channel = connection.createChannel();

        // 3、创建队列，如果队列存在，则使用该队列, 声明一个队列是幂等的 且只有当队列不存在时才会被创建
        /**
         * 参数1：队列名
         * 参数2：是否持久化，true表示MQ重启后队列还在。
         * 参数3：是否私有化，false表示所有消费者都可以访问，true表示只有第一次拥有它的消费者才能访问
         * 参数4：是否自动删除，true表示不再使用队列时自动删除队列（当没有消费者时，就自动删除）
         * 参数5：其他额外参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4、发送消息
        String message = "Hello RabbitMQ";

        /**
         * 参数1：交换机名，""表示默认交换机
         * 参数2：路由键，简单模式就是队列名
         * 参数3：其他额外参数
         * 参数4：要传递的消息字节数组
         */
        channel.basicPublish("",QUEUE_NAME, null, message.getBytes());

        // 5、关闭信道和连接
        channel.close();
        connection.close();
        System.out.println("===消息发送成功===");
    }
}
