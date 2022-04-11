package com.pm490.PM490.controller;

import com.pm490.PM490.dto.PaymentMethodRequest;
import com.pm490.PM490.dto.ProductRequest;
import com.pm490.PM490.model.PaymentMethod;
import com.pm490.PM490.model.Product;
import com.pm490.PM490.service.PaymentMethodService;
import com.pm490.PM490.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paymentmethod")
@PreAuthorize("hasAuthority('CUSTOMER')")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping("/savepaymentmethod")
    public PaymentMethod save(@RequestBody PaymentMethodRequest paymentMethodRequest) {
        System.out.println(" #######CAT "+paymentMethodRequest.getUserId());
        return paymentMethodService.save(paymentMethodRequest);
    }
    @DeleteMapping("/deletepaymentmethod/{id}")
    public Boolean delete(@PathVariable long id){
        return paymentMethodService.delete(id);
    }
}
