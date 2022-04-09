// Author Angie
package com.pm490.PM490.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor

public class Category {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    public Category(String name) {
        this.name = name;
    }
}
