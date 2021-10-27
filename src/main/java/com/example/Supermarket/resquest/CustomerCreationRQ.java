package com.example.Supermarket.resquest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class CustomerCreationRQ {
    private String Name;
    private String address;
    private int age;
    }



