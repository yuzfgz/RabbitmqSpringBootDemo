//package com.consumer;
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.io.ByteArrayInputStream;
//import java.io.ObjectInputStream;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//
//@Component
//public class RabbitConsumerOfDeploy {
//    // 监听队列
//    @RabbitListener(queues = "boot_queue")
//    public void listenMessage(Message message, Channel channel) throws Exception {
//
//        System.out.println("RabbitConsumerOfDeploy11消费消息");
//        // 消息投递序号，消息每次投递该值都会+1
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//        byte[] body = message.getBody();
//        try {
////            int i = 1/0; //模拟处理消息出现 触发ACK
//
//            /**
//             * 签收消息
//             * 参数1：消息投递序号
//             * 参数2：是否一次可以签收多条消息，true:是；false:否
//             */
//            channel.basicAck(deliveryTag, true);
//            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(body));
//            Map<String,String> msgMap = (Map<String,String>) objectInputStream.readObject();
//            String messageId = msgMap.get("messageId");
//            String messageData = msgMap.get("messageContent");
//            String createTime = msgMap.get("sendTime");
//            objectInputStream.close();
////            System.out.println("rabbitConsumer:  messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
////            System.out.println("消费的队列名："+message.getMessageProperties().getConsumerQueue());
//
////            TimeUnit.SECONDS.sleep(1); //模拟处理消息耗时
//        } catch (Exception e) {
//            /**
//             * 拒签消息
//             * 参数1：消息投递序号
//             * 参数2：是否一次可以拒签多条消息，true:是；false:否
//             * 参数3：拒签后消息是否重回队列，true:重回；false:不重回
//             */
//            System.out.println("消息消费失败！");
//            channel.basicNack(deliveryTag, true, true);
//            e.printStackTrace();
//        }
//    }
//}