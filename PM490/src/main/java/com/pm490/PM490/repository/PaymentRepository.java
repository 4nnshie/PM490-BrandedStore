package com.pm490.PM490.repository;

import com.pm490.PM490.model.Account;
import com.pm490.PM490.model.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT p FROM Payment p WHERE p.fromUser.id = :userId")
    List<Payment> findByUserId(@Param("userId") long userId);
}