package com.pm490.PM490.service;

import com.pm490.PM490.dto.PaymentMethodRequest;

import com.pm490.PM490.model.PaymentMethod;


import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethod> findAll();
   // List<PaymentMethod> searchProduct(String searchPro);
    PaymentMethod save(PaymentMethodRequest paymentMethod);
    PaymentMethod update(long id, PaymentMethodRequest paymentMethod);
    boolean delete(long id);
}
