package com.pm490.PM490.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Payments payments;
    private String concept;
    private double amount;
    private LocalDate dateShipped;

    public Transaction(Payments payments, String concept, double amount, LocalDate dateShipped) {
        this.payments = payments;
        this.concept = concept;
        this.amount = amount;
        this.dateShipped = dateShipped;
    }
}
