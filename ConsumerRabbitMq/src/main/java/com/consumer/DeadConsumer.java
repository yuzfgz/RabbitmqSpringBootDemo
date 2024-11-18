package com.consumer;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: rabbitmq
 * @BelongsPackage: com.demo.reliable.consumer
 * @Author: logic
 * @CreateTime: 2022-12-05  20:10
 * @Description: TODO
 */
@Component
public class DeadConsumer {
    @RabbitListener(queues = "boot_queue3")

    public void listenMessage(Message message, Channel channel) throws Exception {
        System.out.println("DeadConsumer消费消息");
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicNack(deliveryTag, true, false); // 消息拒签

    }
}