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
//@RequestMapping("bootRabbitMq2")
//public class RabbitProvider2 {
//
//    // 注入 RabbitTemplate 工具类
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @GetMapping("/sendNewYearMessage")
//    public String sendMessage() {
//        int m = 0;
//
//        for (int i = 0; i < 100; i++) {
//            String messageId = String.valueOf(UUID.randomUUID());   // 随机一个消息 ID
//            String messageContent = "快过年了，提前祝你新年快乐。";   // 消息主题内容
//            String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            HashMap<String, Object> messageMap = new HashMap<>();
//            messageMap.put("messageId", messageId);
//            messageMap.put("messageContent", messageContent);
//            messageMap.put("sendTime", sendTime);
//            /*
//             * 发送消息
//             * 参数1：交换机名称
//             * 参数2：路由 routeKey
//             * 参数3：消息主题内容
//             */
//            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "happyNewYear", messageMap);
//            m++;
//        }
//
//
//
////        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "aaaaaaa", messageMap);
////        rabbitTemplate.convertAndSend("ssss", "happyNewYear", messageMap);
//        return "<p style=color:blue;text-align:center;top:20px;font-size:28px;>新年第" + m + "天的祝福已发送</p>";
//    }
//    @GetMapping("/sendNewYearTtlMessage")
//    public String sendTtlMessage() {
//        System.out.println("sendNewYearTtlMessage");
//        int m=0;
//        // 生产5条消息
//        for (int i = 1; i <= 5; i++) {
//            // 设置消息主题
//            String messageId = String.valueOf(UUID.randomUUID());   // 随机一个消息 ID
//            String messageContent = "快过年了，提前祝你新年快乐。第 "+i+"封信";   // 消息主题内容
//            String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            HashMap<String, Object> messageMap = new HashMap<>();
//            messageMap.put("messageId", messageId);
//            messageMap.put("messageContent", messageContent);
//            messageMap.put("sendTime", sendTime);
//            messageMap.put("expiration",20);
//
//            if(i == 4) {
//                /*
//                 * 模拟生成一条具有有效时间为1秒的消息
//                 * */
//                // 设置消息属性
//                MessageProperties messageProperties = new MessageProperties();
//                // 设置消息存活时间
//                messageProperties.setExpiration("1000");
////                messageProperties.setPriority(10);//设置优先级
//                // 创建消息对象
//                Message message = new Message(messageContent.getBytes(StandardCharsets.UTF_8), messageProperties);
//                // 发送消息
//                rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "happyNewYear", message);
//            } else {
//                /*
//                 * 发送消息
//                 * 参数1：交换机名称
//                 * 参数2：路由 routeKey
//                 * 参数3：消息主题内容
//                 */
//                rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "happyNewYear", messageMap);
//
//            }
//            m++;
//        }
//        return "<p style=color:blue;text-align:center;top:20px;font-size:28px;>第"+m+"封新年祝福信已发送</p>";
//    }
//
//}
