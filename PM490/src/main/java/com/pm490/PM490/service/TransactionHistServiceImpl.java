package com.pm490.PM490.service;

import com.pm490.PM490.model.Transaction;
import com.pm490.PM490.model.TransactionHist;
import com.pm490.PM490.repository.TransactionHistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionHistServiceImpl implements TransactionHistService {

    @Autowired
    TransactionHistRepository repository;

    @Override
    public void saveTransactionHist(Transaction transaction, boolean response, LocalDate date, LocalTime time) {
        TransactionHist transactionhist = new TransactionHist(transaction, response, date, time);
        repository.save(transactionhist);

    }


    @Override
    public List<TransactionHist> getAllTransactionHist() {
        return repository.findAll();
    }


    @Override
    public double getTotalTransaction(boolean response) {
        List<TransactionHist> transaction = getAllTransactionHist();
        double totalAmount = 0.0;
        for (TransactionHist trans : transaction) {
            if (trans.getResponse() == response) {
                totalAmount += trans.getTransaction().getAmount();
            }
        }
        return totalAmount;
    }

    @Override
    public double getTotalTransactionAmt() {
        List<TransactionHist> transaction = getAllTransactionHist();
        double totalAmount = 0.0;
        for (TransactionHist trans : transaction) {
            totalAmount += trans.getTransaction().getAmount();
        }
        return totalAmount;
    }

    @Override
    public Map<Month, Double> getTransactionByMonth(int year) {
        //List<TransactionHist> transaction = getAllTransactionHist();
        List<TransactionHist> alltransactiions = repository.findAll();

        return alltransactiions.stream().collect(
                Collectors.groupingBy(TransactionHist::getMonth, Collectors.summingDouble(TransactionHist::getTransactionAmount)));
        //.summingDouble(TransactionHist::getTransaction))
    }

}