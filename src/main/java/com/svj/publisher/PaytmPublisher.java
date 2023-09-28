package com.svj.publisher;

import com.svj.config.MessagingConfig;
import com.svj.dto.PaymentRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PaytmPublisher {

    @Autowired
    private RabbitTemplate template;

    public void doPaymentTransaction(PaymentRequest request){
        request.setTransactionId(UUID.randomUUID().toString());
        request.setTxDate(LocalDate.now());
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, request);
    }
}
