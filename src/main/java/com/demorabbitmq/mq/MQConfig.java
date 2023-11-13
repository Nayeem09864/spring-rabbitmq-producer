package com.demorabbitmq.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class MQConfig {
    public static final String queue = "message_queue";

    public static final String queue2 = "message_queue_2";
    public static final String exchange = "message_exchange";

    public static final String exchange2 = "message_exchange2";
    public static final String routingKey = "message_routing_key";

    public static final String routingKey2 = "message_routing_key2";
    @Bean
    public Queue queue() {
        return  new Queue(queue);
    }

    @Bean
    public Queue queue2() {
        return new Queue(queue2);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public TopicExchange exchange2() {
        return new TopicExchange(exchange2);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    public Binding binding2(Queue queue2, TopicExchange exchange2) {
        return BindingBuilder.bind(queue2).to(exchange2).with(routingKey2);
    }

//    public MessageConverter messageConverter() {
//        return (MessageConverter) new Jackson2JsonMessageConverter();
//    }
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    public AmqpTemplate template2(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

}
