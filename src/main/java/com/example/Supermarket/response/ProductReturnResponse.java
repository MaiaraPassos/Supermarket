package com.example.Supermarket.response;

import com.example.Supermarket.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductReturnResponse {
    private Long id;
    private String description;
    private float price;

    @JsonIgnore
    public ProductReturnResponse createProductReturnResponse(Product product) {
        this.id = product.getId();
        this.description = product.getDescription();
        this.price = product.getPrice();
        return this;
    }
}
