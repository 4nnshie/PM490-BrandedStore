package com.pm490.PM490.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.List;
=======
import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
>>>>>>> Stashed changes

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor

public abstract class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Embedded
    private Address address;
    @OneToMany
    private List<Payment> paymentList = new ArrayList<>();
    @OneToMany
    private List<Account> accountList = new ArrayList<>();



    public User(String name, String password, UserStatus status, String phone, String email, Role role, Address address) {
        this.name = name;
        this.password = password;
        this.status = status;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.address = address;

    }
}
