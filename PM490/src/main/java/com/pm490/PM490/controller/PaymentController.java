package com.pm490.PM490.controller;

import com.pm490.PM490.model.Payment;
import com.pm490.PM490.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
//@PreAuthorize("hasAuthority('ADMIN')")

public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    long createCard(@RequestBody Payment payment) {
        return paymentService.addPayment(payment);
    }


}
