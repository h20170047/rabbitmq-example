package com.svj.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svj.config.MessagingConfig;
import com.svj.dto.PaymentRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaytmClientApp3 {

    private ObjectMapper objectMapper;

    public PaytmClientApp3(ObjectMapper objectMapper){
        this.objectMapper= objectMapper;
    }

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void processPaymentRequest(PaymentRequest request){
        try {
            System.out.println("Consumer (3) consumes "+objectMapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
