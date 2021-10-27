package com.example.Supermarket.response;

import com.example.Supermarket.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomerReturnResponse<CustomerReturnResponse> {
    private Long id;
    private String name;
    private int age;
    private List<PurchaseReturnResponse> purchasesResp;

    @JsonIgnore
    public CustomerReturnResponse createResponseFromCustomer(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.age = customer.getAge();
        return this;
    }
}
