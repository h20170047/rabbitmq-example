package com.svj.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    private ObjectMapper primaryObjectMapper;

    public MessagingConfig(ObjectMapper objectMapper){
        this.primaryObjectMapper= objectMapper;
    }

    public static final String QUEUE = "patym_queue";
    public static final String EXCHANGE = "paytm_exchange";
    public static final String ROUTING_KEY = "paytm_routingKey";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    @Bean
    public Exchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory factory){
        RabbitTemplate template= new RabbitTemplate(factory);
        template.setMessageConverter(convertor());
        return template;
    }


    @Bean
    public MessageConverter convertor() {
        return new Jackson2JsonMessageConverter(primaryObjectMapper);
    }

}
