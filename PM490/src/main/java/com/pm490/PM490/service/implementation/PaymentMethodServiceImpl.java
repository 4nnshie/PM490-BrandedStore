package com.pm490.PM490.service.implementation;
/*
import com.pm490.PM490.dto.PaymentMethodRequest;
import com.pm490.PM490.dto.ProductRequest;
import com.pm490.PM490.model.*;
import com.pm490.PM490.repository.*;
import com.pm490.PM490.service.PaymentMethodService;
import com.pm490.PM490.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentMethodServiceImpl implements PaymentMethodService {
    @Autowired
    public PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public UserRepository userRepository;

    @Override
    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod save(PaymentMethodRequest paymentMethod) {
        User user = userRepository.findById(paymentMethod.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with id :" + paymentMethod.getUserId()));

        PaymentMethod paymentMethod1 = new PaymentMethod(user,
                paymentMethod.getRole(),
                paymentMethod.getType(),
                paymentMethod.getFullname(),
                paymentMethod.getNumber(),
                paymentMethod.getExpireDate(),
                paymentMethod.getCvv(),
                paymentMethod.getZipcode());
                return paymentMethodRepository.save(paymentMethod1);
    }

    @Override
    public PaymentMethod update(long id, PaymentMethodRequest paymentMethod) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        Optional<PaymentMethod> optionalProduct = Optional.ofNullable(paymentMethodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment doesn't exist with id :" + id)));
        PaymentMethod paymentMethod = optionalProduct.get();
        if (optionalProduct.isPresent()) {

            paymentMethodRepository.delete(paymentMethod);
            return !paymentMethodRepository.existsById(id);
        } else {
            return false;
        }
    }

}
*/