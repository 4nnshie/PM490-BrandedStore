package com.pm490.PM490.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;

public class BankCardDto {
    private String cardNumber;
    private String holderName;
    private String bankName;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate expirationDate;
    private String cvv;
    private boolean cardStatus = true;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BankCardDto))
            return false;
        BankCardDto b = (BankCardDto) o;
        if (b.getCardNumber() != this.cardNumber)
            return false;
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.cardNumber);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public boolean isCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(boolean cardStatus) {
        this.cardStatus = cardStatus;
    }

}