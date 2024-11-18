//package com.consumer;
//
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//@RabbitListener(queues = "boot_queue")
//public class ConsumerController {
//
//    @RabbitHandler  // @RabbitListener 当有收到消息的时候，就交给 @RabbitHandler 的方法处理，根据接受的参数类型进入具体的方法中。
//    public void listenMessage(Map messageContent) {
//        System.out.println("topicReceiver消费者收到新年祝福：" + messageContent.toString());
//    }
//
//}
