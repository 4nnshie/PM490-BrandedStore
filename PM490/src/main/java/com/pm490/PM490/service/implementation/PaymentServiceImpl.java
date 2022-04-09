package com.pm490.PM490.service.implementation;

import com.pm490.PM490.model.OrderCart;
import com.pm490.PM490.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.transform.Result;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public OrderCart getOrderCart(int id) {
        return null;
    }

    @Override
    public Result payment(OrderCart ordeCart, boolean newAddress) {

        return null;
    }

}
