package com.pm490.PM490.service;

import com.pm490.PM490.model.Card;

import java.util.List;

public interface CardService {

//    public void addCard(Card card);
//    public Card getCard(String cardNumber);
//    public List<Card> getAllCards();
//    public boolean validateDate(int year, int month);
//    public boolean validateCVV(int cvv);
//    public String getCardType(String cardNumber);

    /**
     * add new card
     * @param card Card
     * @return card Id
     */
    long addCard(Card card);


}