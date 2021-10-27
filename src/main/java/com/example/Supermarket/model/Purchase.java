package com.example.Supermarket.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean isPaid;

    @ManyToMany
    @JoinTable(name = "purchase_product",
            joinColumns = @JoinColumn(name = "purchaseId"),
            inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<Product> purchase_product;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
}
