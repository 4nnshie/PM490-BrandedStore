package com.pm490.PM490.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class TransactionHist {

    @Id
    @GeneratedValue
    private String id;
    @OneToOne
    private Transaction transaction;
    private boolean response;
    private LocalDate date;
    private LocalTime time;

    public TransactionHist(Transaction transaction, boolean response, LocalDate date, LocalTime time) {
        this.transaction = transaction;
        this.response = response;
        this.date = date;
        this.time = time;

    }

    public Transaction getTransaction() {
        return transaction;
    }

    public boolean getResponse() {
        return response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
    public Month getMonth() {
        return date.getMonth();
    }
    public Double getTransactionAmount() {
        return transaction.getAmount();
    }
}
