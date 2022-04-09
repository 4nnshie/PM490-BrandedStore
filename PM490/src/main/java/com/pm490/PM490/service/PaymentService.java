package com.pm490.PM490.service;

import com.pm490.PM490.model.OrderCart;

import javax.xml.transform.Result;

public interface PaymentService {

        OrderCart getOrderCart(int id);
        Result payment(OrderCart ordeCart, boolean newAddress);

}
