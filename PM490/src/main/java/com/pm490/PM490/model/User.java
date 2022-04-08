package com.pm490.PM490.model;

import javax.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Embedded
    private Address address;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Set<Role> getRoles() {
        return new HashSet<>(Arrays.asList(role));
    }
}
