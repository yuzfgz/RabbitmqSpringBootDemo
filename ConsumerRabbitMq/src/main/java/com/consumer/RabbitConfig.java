package com.consumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    // 指定交换机名称
    public final static String EXCHANGE_NAME = "boot_exchange";
    // 指定队列名
    private final static String QUEUE_NAME = "boot_queue3";

    // 定义死信交换机
    private final static String DEAD_EXCHANGE = "dead_exchange";
    // 定义死信队列
    private final static String DEAD_QUEUE = "dead_queue";

    // 指定队列名
    private final static String ROUTINGKEY = "#.happyNewYear.#";
    // 指定队列名
    private final static String DEADROUTING = "#.deadRouting.#";

    @Bean
    public MessageConverter jsonToMapMessageConverter() {
        DefaultClassMapper defaultClassMapper = new DefaultClassMapper();
        // 指定反序列化期间要信任的一组包，星号 ( * ) 表示全部信任
        defaultClassMapper.setTrustedPackages("com.*");
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        jackson2JsonMessageConverter.setClassMapper(defaultClassMapper);
        return jackson2JsonMessageConverter;
    }


    // 死信交换机
    @Bean(DEAD_EXCHANGE)
    public Exchange deadExchange() {
        return ExchangeBuilder
                .topicExchange(DEAD_EXCHANGE)
                .durable(true) // 持久化
                .build();
    }

    // 死信队列
    @Bean(DEAD_QUEUE)
    public Queue deadQueue() {
        return QueueBuilder
                .durable(DEAD_QUEUE)
                .build();
    }

    // 死信交换机绑定死信队列
    @Bean
    public Binding bindDeadQueue(@Qualifier(DEAD_EXCHANGE) Exchange exchange, @Qualifier(DEAD_QUEUE) Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(DEADROUTING)
                .noargs();
    }







    // 创建交换机
    @Bean("bootExchange")
    public Exchange getExchange() {
        return ExchangeBuilder
                .topicExchange(EXCHANGE_NAME)
                .durable(true) // 是否持久化
                .build();
    }



    // 创建队列 并设置队列的过期时间
    @Bean("bootQueue")
    public Queue getSecondMessageQueue() {
        return QueueBuilder
                .durable(QUEUE_NAME)
                .deadLetterExchange(DEAD_EXCHANGE) // 绑定死信交换机
                .deadLetterRoutingKey("deadRouting") // 死信队列路由关键字

                .maxLength(10)  // 队列最大长度
//                .maxPriority(10) // 设置优先级参数值
                .ttl(15000) // 设定该队列里所有消息的存活时间是 15秒
                .build();
    }


    // 创建交换机绑定队列
    @Bean
    public Binding bindingExchangeQueue(@Qualifier("bootExchange") Exchange exchange, @Qualifier("bootQueue") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("happyNewYear")   // 通配符模式 要匹配的路由键 RoutingKey
                .noargs();
    }

}
