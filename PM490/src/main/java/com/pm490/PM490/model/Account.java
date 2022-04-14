package com.pm490.PM490.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User user;
    private long accountNumber;
    private int routingNumber;
    private int zipCode;

<<<<<<< Updated upstream
    public Account(TypeAccount type, double balance, String concept, double taxAmount, LocalDate date) {
        this.type = type;
        this.balance = balance;
        this.concept = concept;
        this.taxAmount = taxAmount;
        this.date = date;
=======
    public Account(User user, long accountNumber, int routingNumber, int zipCode) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.zipCode = zipCode;
>>>>>>> Stashed changes
    }
}
