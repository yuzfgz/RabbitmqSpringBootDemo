package com;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfirmConfig {
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启 Mandatory 强制执行调用回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * 被调用的回调方法
             * @param correlationData 相关配置信息
             * @param ack 交换机是否成功收到消息 可以根据 ack 做相关的业务逻辑处理
             * @param cause 失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack) {
                    // 控制台 打印输出内容
                    System.out.println("生产者已成功将消息推送到交换机");
                } else {
                    // 控制台 打印输出内容
                    System.out.println("ConfirmInfo:     "+"相关配置信息: "+correlationData);
                    System.out.println("ConfirmInfo:     "+"确认结果: "+ack);
                    System.out.println("ConfirmInfo:     "+"原因: "+cause);

                    // 可做针对性地业务逻辑处理，例如：让消息重发、发送邮件通知程序员、做日志等等。
                }
            }
        });
        // 新增退回模式回调函数
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            /**
             * 被调用的回调方法
             * @param returnedMessage 消息主题内容对象
             */
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println("ReturnInfo:     "+"消息对象："+returnedMessage.getMessage());
                System.out.println("ReturnInfo:     "+"错误码："+returnedMessage.getReplyCode());
                System.out.println("ReturnInfo:     "+"错误信息："+returnedMessage.getReplyText());
                System.out.println("ReturnInfo:     "+"交换机："+returnedMessage.getExchange());
                System.out.println("ReturnInfo:     "+"路由键："+returnedMessage.getRoutingKey());

                // 可做针对性地业务逻辑处理，例如：发送邮件通知程序员、做日志等等。
            }
        });

        return rabbitTemplate;
    }
}