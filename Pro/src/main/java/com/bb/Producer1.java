package com.demo01.bb;

import com.demo01.cc.ConnectionFactoryUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer1 {

    private static final String SEND_MESSAGE = "SEND_MESSAGE";
    private static final String SEND_MAIL = "SEND_MAIL";
    private static final String SEND_STATION = "SEND_STATION";
    private static final String EXCHANGE_FANOUT = "exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、建立工厂连接
        Connection connection = ConnectionFactoryUtil.getConnection();

        // 2、创建信道
        Channel channel = connection.createChannel();

        // 3、创建交换机
        /**
         * 参数1：交换机名
         * 参数2：交换机类型
         * 参数3：交换机持久化
         */
        channel.exchangeDeclare(EXCHANGE_FANOUT, BuiltinExchangeType.FANOUT,true);

        // 4、创建队列
        // 短信
        channel.queueDeclare(SEND_MESSAGE, true, false, false, null);
        // 邮件
        channel.queueDeclare(SEND_MAIL, true, false, false, null);
        // 站内信
        channel.queueDeclare(SEND_STATION, true, false, false, null);

        // 5、交换机绑定队列
        /**
         * 参数1：队列名
         * 参数2：交换机名
         * 参数3：路由关键字，发布订阅模式写 ""即可
         */
        channel.queueBind(SEND_MAIL, EXCHANGE_FANOUT, "");

        channel.queueBind(SEND_MESSAGE, EXCHANGE_FANOUT, "");

        channel.queueBind(SEND_STATION, EXCHANGE_FANOUT, "");

        // 6、发送消息
        for (int i = 0; i < 10; i++) {
            channel.basicPublish(EXCHANGE_FANOUT, "", null, ("尊敬的Vip用户，秒杀商品开抢了！"+i).getBytes());
        }

        // 7、关闭资源
        channel.close();

        connection.close();
    }
}
