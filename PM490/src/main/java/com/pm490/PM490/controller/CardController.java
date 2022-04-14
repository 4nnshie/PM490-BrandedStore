package com.pm490.PM490.controller;

import com.pm490.PM490.model.Card;
import com.pm490.PM490.service.CardService;
import com.pm490.PM490.service.PaymentService;
import com.pm490.PM490.service.TransactionHistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
@PreAuthorize("hasAuthority('ADMIN')")

public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private TransactionHistService histService;

    @Autowired
    private PaymentService payment;

    @PostMapping("/create")
    long createCard(@RequestBody Card card) {
        return cardService.addCard(card);
    }

//    @GetMapping("/getcard/{cardnumber}")
//    public ResponseEntity<?> getCard(@PathVariable("cardnumber") String cardnumber) {
//        Card card = cardService.getCard(cardnumber);
//        return new ResponseEntity<Card>(card, HttpStatus.OK);
//    }

}
