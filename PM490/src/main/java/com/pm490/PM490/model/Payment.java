<<<<<<< Updated upstream
package com.pm490.PM490.model;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor

public class Payment {
    @Id
    @GeneratedValue
    private long id;
    private String type;
    private String fullname;
    private long number;
    private LocalDate expireDate;
    private int cvv;
    private int zipcode;

    public Payment(String type, String fullname, long number, LocalDate expireDate, int cvv, int zipcode) {
        this.type = type;
        this.fullname = fullname;
        this.number = number;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.zipcode = zipcode;
    }
}
=======
package com.pm490.PM490.model;

import lombok.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User fromUser;
    @ManyToOne
    private User toUser;
    //@ManyToOne
    //private PaymentMethod paymentMethod;
    private double amount;
    private LocalDate date;
    private String concept;
    private String status;

    public Payment(User fromUser, User toUser, /*PaymentMethod paymentMethod,*/double amount, LocalDate date, String concept, String status) {
        this.fromUser = fromUser;
        this.toUser = toUser;
       // this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.date = date;
        this.concept = concept;
        this.status = status;

    }
}
>>>>>>> Stashed changes
