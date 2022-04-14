package com.pm490.PM490.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderCart {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Customer customer;
    private LocalDate dateOrdered;
    private LocalDate dateShipped;
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;
    @ManyToMany
    private List<ItemList> items;

    public OrderCart(Customer customer, LocalDate dateOrdered, LocalDate dateShipped, PurchaseStatus status, List<ItemList> items) {
        this.customer = customer;
        this.dateOrdered = dateOrdered;
        this.dateShipped = dateShipped;
        this.status = status;
        this.items = items;
    }

//    public void setItems(List<ItemList> items) {
//        this.items = items;
//    }
}