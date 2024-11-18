//package com;
//
//
//import cn.hutool.core.lang.UUID;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.nio.charset.StandardCharsets;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//
//@RestController
//@RequestMapping("bootRabbitMq")
//public class RabbitProvider {
//
//    // 注入 RabbitTemplate 工具类
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @GetMapping("/sendNewYearMessage")
//    public String sendMessage() {
//        String messageId = String.valueOf(UUID.randomUUID());   // 随机一个消息 ID
//        String messageContent = "快过年了，提前祝你新年快乐。";   // 消息主题内容
//        String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        HashMap<String, Object> messageMap = new HashMap<>();
//        messageMap.put("messageId", messageId);
//        messageMap.put("messageContent", messageContent);
//        messageMap.put("sendTime", sendTime);
//
//        /*
//         * 发送消息
//         * 参数1：交换机名称
//         * 参数2：路由 routeKey
//         * 参数3：消息主题内容
//         */
//        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "happyNewYear", messageMap);
////        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "aaaaaaa", messageMap);
////        rabbitTemplate.convertAndSend("ssss", "happyNewYear", messageMap);
//        return "<p style=color:blue;text-align:center;top:20px;font-size:28px;>新年祝福已发送</p>";
//    }
//
//
//
//}
