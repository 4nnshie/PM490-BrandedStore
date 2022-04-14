package com.pm490.PM490.model;

import lombok.*;

import javax.persistence.*;
<<<<<<< Updated upstream
import javax.validation.constraints.Size;
@Entity
=======

>>>>>>> Stashed changes
@Data
@NoArgsConstructor
@Embeddable

public class Address {
    private String country;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    public Address(String country, String street, String city, String state, String zipCode) {
        this.country = country;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
