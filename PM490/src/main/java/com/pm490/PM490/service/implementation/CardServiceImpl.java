package com.pm490.PM490.service.implementation;

import com.pm490.PM490.model.Card;
import com.pm490.PM490.repository.CardRepository;
import com.pm490.PM490.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public long addCard(Card card) {
        Card addedCard = cardRepository.save(card);
        //...
        return addedCard.getId();
    }


    //    @Override
//    public Card getCard(String cardnumber) {
//        Optional<Card> card = cardRepository.findById(cardnumber);
//        return card.orElse(null);
//    }
//
//    @Override
//    public List<Card> getAllCards() {
//        return cardRepository.findAll();
//    }
//
//
//    public boolean validateDate(int year, int month) {
//        LocalDate ld = LocalDate.now();
//        if (year < ld.getYear() || (year == ld.getYear() && month < ld.getMonthValue())) return false;
//
//        return true;
//    }
//
//
//    public boolean validateCVV(int cvv) {
//        String newcvv = String.valueOf(cvv);
//        if (newcvv.length() != 3)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public String getCardType(String cardnumber) {
//        if (cardnumber.length() < 13)
//            return null;
//        int num = Integer.parseInt(cardnumber.substring(0, 4));
//        List<PaymentMethod> method = cardPayment.findAll();
//        String cardtype = method.stream().filter(p -> p.getRangeFrom() <= num && p.getRangeTo() >= num).map(p -> p.getName()).findFirst().orElse(null);
//
//        return cardtype;
//    }
//
//
//    @Override
//    public String addNewCard(Card card) {
//
//        int year = card.getYear();
//        int month = card.getMonth();
//        int cvv = card.getCvv();
//        String cardNumber = card.getCardNumber();
//        String cardholder = card.getCardHolder();
//        String address = card.getBillingAddress();
//
//        if (!validateDate(year, month))
//            return "Card expiration date is invalid";
//        if (!validateCVV(cvv))
//            return "Card number is invalid";
//        Card newcard = new Card(user, cardNumber, expMonth, expYear, cvvCode);
//        addCard(newcard);
//        return "Card added successfully";
//
//    }
}