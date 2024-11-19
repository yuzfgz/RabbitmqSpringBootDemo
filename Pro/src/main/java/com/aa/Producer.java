package com.demo01.aa;
import com.demo01.cc.ConnectionFactoryUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
public class Producer {
    // 定义交换机名称
    private final static String ROUTE_NAME = "exchange_routing";

    // 大促销路由 RoutingKey
    private final static String BIG_ROUT_KEY = "big";

    // 小促销路由 RoutingKey
    private final static String SMALL_ROUT_KEY = "small";

    public static void main(String[] args) throws IOException, TimeoutException {
        //  1、建立工厂连接
        Connection connection = ConnectionFactoryUtil.getConnection();

        // 2、创建信道
        Channel channel = connection.createChannel();

        // 3、建立交换机
        channel.exchangeDeclare("exchange_routing", BuiltinExchangeType.DIRECT, true);

        // 4、创建队列
        channel.queueDeclare("SEND_MESSAGE2", true, false, false, null);
        channel.queueDeclare("SEND_STATION2", true, false, false, null);
        channel.queueDeclare("SEND_EMAIL2", true, false, false, null);

        // 5、交换机通过 RoutingKey 关键字绑定队列
        channel.queueBind("SEND_MESSAGE2", ROUTE_NAME, BIG_ROUT_KEY);
        channel.queueBind("SEND_EMAIL2", ROUTE_NAME, BIG_ROUT_KEY);
        channel.queueBind("SEND_STATION2", ROUTE_NAME, BIG_ROUT_KEY);
        channel.queueBind("SEND_STATION2", ROUTE_NAME, SMALL_ROUT_KEY);

        // 6、发送消息
        channel.basicPublish(ROUTE_NAME, BIG_ROUT_KEY, null, ("双十一大促销活动--全场买一送一").getBytes());
        channel.basicPublish(ROUTE_NAME, SMALL_ROUT_KEY, null, ("小促销活动--满1000立减200").getBytes());

        // 7、关闭资源
        channel.close();
        connection.close();
    }
}
