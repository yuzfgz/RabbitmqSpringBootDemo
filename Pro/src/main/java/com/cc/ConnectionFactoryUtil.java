package com.demo01.cc;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionFactoryUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        // 建立工厂连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置服务地址
        connectionFactory.setHost("127.0.0.1");
        // 设置端口
        connectionFactory.setPort(5672);
        // 设置连接登录账号
        connectionFactory.setUsername("guest");
        // 设置连接登录密码
        connectionFactory.setPassword("guest");
        // 设置虚拟主机机vhost 这里默认跟目录 "/"
        connectionFactory.setVirtualHost("/");

        // 建立连接
        Connection connection = connectionFactory.newConnection();
        return connection;
    }
}
