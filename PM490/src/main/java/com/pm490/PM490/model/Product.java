// Author Angie
package com.pm490.PM490.model;

import lombok.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String color;
    @ManyToOne
    private Vendor vendor;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    private int quantity;
    @ManyToOne
    private Category category;
    private double price;

    public Product(String name, String color, Vendor vendor, ProductStatus status, int quantity, Category category, double price) {
        this.name = name;
        this.color = color;
        this.vendor = vendor;
        this.status = status;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
    }
}
