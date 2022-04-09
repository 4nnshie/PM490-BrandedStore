package com.pm490.PM490.controller;

import com.pm490.PM490.model.OrderCart;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

public class PaymentController {

    @RequestMapping("/payment")
    public String payment(){
        return null;
    }

    @RequestMapping("/pay")
    public String payment(@Valid OrderCart orderCart){
        return null;

    }
}
