package com.pm490.PM490.service;

import com.pm490.PM490.model.Payment;

import java.util.List;

public interface PaymentService {

    /**
     * Make payment
     * @param payment Payment
     * @return payment Id
     */
    public long makePayment(Payment payment);

    /**
     * Add payment
     * @param payment Payment
     * @return payment Id
     */
    public long addPayment(Payment payment);


//    public List<Payment> getAllPayment();
//
//    public Payment findPaymentMethodByname(String name);
//
//    public Payment findPaymentMethodById(String id);
//
//    public void deletePaymentMethod(String id);

}