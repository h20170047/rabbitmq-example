package com.svj.controller;

import com.svj.dto.PaymentRequest;
import com.svj.publisher.PaytmPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaytmController {

    private PaytmPublisher publisher;

    public PaytmController(PaytmPublisher publisher){
        this.publisher= publisher;
    }

    @PostMapping("/mq/pay")
    public String payUsingUPI(@RequestBody PaymentRequest request){
        publisher.doPaymentTransaction(request);
        return "Payment request in process...";
    }
}
