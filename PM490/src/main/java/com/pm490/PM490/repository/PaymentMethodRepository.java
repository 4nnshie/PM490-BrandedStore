package com.pm490.PM490.repository;

import com.pm490.PM490.model.PaymentMethod;
import com.pm490.PM490.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Long> {

}
