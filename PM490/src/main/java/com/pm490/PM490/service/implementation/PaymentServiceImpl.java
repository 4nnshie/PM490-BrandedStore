package com.pm490.PM490.service.implementation;

import com.pm490.PM490.model.Card;
import com.pm490.PM490.model.Payment;
import com.pm490.PM490.repository.PaymentRepository;
import com.pm490.PM490.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public long makePayment(Payment payment) {
        Payment madePayment = paymentRepository.save(payment);
        //...
        return madePayment.getId();
    }

    @Override
    public long addPayment(Payment payment) {
        Payment addedPayment = paymentRepository.save(payment);
        //...
        return addedPayment.getId();
    }



//    @Override
//    public Payment findPaymentMethodByname(String name) {
//        return repository.findPaymentMethodByName(name);
//    }
//
//    @Override
//    public List<Payment> getAllPaymentMethod() {
//        return repository.findAll();
//    }
//
//
//    @Override
//    public Payment findPaymentMethodById(String id) {
//        return repository.findById(id).get();
//    }
//
//    @Override
//    public void deletePaymentMethod(String id) {
//        repository.deleteById(id);
//    }

}

