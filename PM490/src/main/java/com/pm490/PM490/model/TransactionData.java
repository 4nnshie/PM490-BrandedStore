package com.pm490.PM490.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TransactionData {
    private double total;
    private double success;
    private double fail;
    private double balance;

    public TransactionData(double total, double success, double fail) {
        this.total = total;
        this.success = success;
        this.fail = fail;
    }
}