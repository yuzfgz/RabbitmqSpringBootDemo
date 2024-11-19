package com.demo01.dd;
import com.demo01.cc.ConnectionFactoryUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String ROUTE_NAME = "exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、建立工厂连接
        Connection connection = ConnectionFactoryUtil.getConnection();

        // 2、建立信道
        Channel channel = connection.createChannel();

        // 3、建立交换机
        channel.exchangeDeclare("exchange_topic", BuiltinExchangeType.TOPIC, true);

        // 4、创建队列
        channel.queueDeclare("SEND_MAIL3", true, false, false, null);
        channel.queueDeclare("SEND_MESSAGE3", true, false, false, null);
        channel.queueDeclare("SEND_STATION3", true, false, false, null);

        // 5、交换机绑定队列
        channel.queueBind("SEND_MAIL3",ROUTE_NAME,"#.big.#");
        channel.queueBind("SEND_MESSAGE3",ROUTE_NAME,"#.middle.#");
        channel.queueBind("SEND_STATION3",ROUTE_NAME,"#.small.#");

        // 6、发送消息
        channel.basicPublish(ROUTE_NAME, "big.middle.small", null, ("双十一大促销活动--全场买一送一").getBytes());
        channel.basicPublish(ROUTE_NAME, "small",null, ("小促销活动--满1000立减200").getBytes());

        // 7、关闭资源
        channel.close();
        connection.close();

    }
}
