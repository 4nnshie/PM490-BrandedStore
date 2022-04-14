package com.pm490.PM490.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User user;
    private String cardNumber;
    private CardType cardType;
    private int expMonth;
    private int expYear;
    private int cvvCode;
    private int zipCode;

    public Card(User user, String cardNumber, CardType cardType, int expMonth, int expYear, int cvvCode, int zipCode) {
        this.user = user;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvvCode = cvvCode;
        this.zipCode = zipCode;
    }
}
