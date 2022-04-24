//author oyunbold, philipose, saruulgun, munkdalai
package com.pm490.PM490.controller;

import com.pm490.PM490.model.*;
import com.pm490.PM490.payload.request.PaymentRequest;
import com.pm490.PM490.payload.response.PaymentResponse;
import com.pm490.PM490.repository.OrderRepository;
import com.pm490.PM490.repository.TransactionRepository;
import com.pm490.PM490.util.BankResponseServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/payment")
@PreAuthorize("hasAuthority('CUSTOMER')")
public class PaymentController {

    @Autowired
    private BankResponseServiceUtil bankResponseServiceUtil;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<PaymentResponse> pay(@RequestBody PaymentRequest paymentRequest) {
        double total = paymentRequest.getOrderCart().getItems().stream().mapToDouble(itemList -> itemList.getTotal()).sum();
        boolean bankRes = bankResponseServiceUtil.bankResponse(total, paymentRequest.getOrderCart().getCustomer().getId());
        PaymentResponse paymentResponse = new PaymentResponse();

        if (bankRes) {
            // craete txn and save into db
            Transaction transaction = new Transaction();
            transaction.setUser(paymentRequest.getOrderCart().getCustomer());
            transaction.setPaymentMethod(paymentRequest.getPaymentMethod());
            transaction.setAmount(total);
            transaction.setConcept("");
            transaction.setDateShipped(LocalDate.now());
            transactionRepository.save(transaction);

            OrderCart orderCart = paymentRequest.getOrderCart();
            orderCart.setDateShipped(LocalDate.now());
            orderCart.setStatus(PurchaseStatus.ORDERED);
            orderRepository.save(orderCart);

            // amjilttai response butsaan.
            paymentResponse.info = "Purchased";
            return ResponseEntity.ok(paymentResponse);
        } else {
            // response butsaana.
            paymentResponse.info = "Failed to purchase";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(paymentResponse);
        }
    }


}
