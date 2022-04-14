package com.pm490.PM490.service;

import com.pm490.PM490.model.Transaction;
import com.pm490.PM490.model.TransactionHist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Map;


public interface TransactionHistService {

    void saveTransactionHist(Transaction transaction, boolean response, LocalDate date, LocalTime time);

    List<TransactionHist> getAllTransactionHist();

    double getTotalTransaction(boolean response);

    double getTotalTransactionAmt();

    Map<Month, Double> getTransactionByMonth(int i);

}