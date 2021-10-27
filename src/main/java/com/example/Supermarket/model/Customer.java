package com.example.Supermarket.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@Setter
@Builder
@Entity
@Table(name="costumer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;
    @Min(value = 0)
    private int age;
    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;


    }





