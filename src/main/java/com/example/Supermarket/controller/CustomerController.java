package com.example.Supermarket.controller;

import com.example.Supermarket.model.Customer;
import com.example.Supermarket.response.CustomerReturnResponse;
import com.example.Supermarket.resquest.CustomerCreationRQ;
import com.example.Supermarket.service.CustomerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api")
@Validated

public class CustomerController {
    private final CustomerService customerService;
    private final CustomerService;

    public CustomerController(CustomerService customerServ) {
        this.customerService = customerServ;
    }


    //Get all customers
    @GetMapping("/customers/")
    public List<CustomerReturnResponse> customerService(Scanner customerServ) {
        List<CustomerReturnResponse> customersRetTemp = new ArrayList<>();
            return customersRetTemp;
        }

    //Get customer by id
    @GetMapping("/customers/{id}")
    public Object CustomerNotFound(@PathVariable(value = "id") Long id, CustomerService customerServ, Object CustomerNotFound) {
        Customer customer = customerServ.CustomerNotFound(id, CustomerNotFound);
        return new CustomerReturnResponse()
                .createResponseFromCustomer(customer);
    }

    //Create customer
    @PostMapping(value = "/customers")
    public CustomerReturnResponse createCustomer(@RequestBody CustomerCreationRQ customerTemp) {
        //Transforms a CustomerCreationRequest received by parameter into a Customer that will be sent into customerServ.save
        Customer newCustomer = Customer.builder()
                .name(customerTemp.getName())
                .age(customerTemp.getAge())
                .build();
        customerService.save(newCustomer);

        //Transforms the returned Customer from customerServ.save() into a CustomerReturnResponse and returns it
        return new CustomerReturnResponse()
                .createResponseFromCustomer(newCustomer);
    }

    //Update customer
    @PutMapping(value = "customers/{id}")
    public CustomerReturnResponse updateCustomer(@PathVariable(value = "id") Long id, @RequestBody CustomerCreationRQ customerReq) {
        Customer customer = customerService.update(customerReq, id);
        //Transforms the returned Customer from customerServ.update() into a CustomerReturnResponse and returns it
        return new CustomerReturnResponse()
                .createResponseFromCustomer(customer);
    }

    //Delete customer
    @DeleteMapping(value = "/customers/{id}")
    public void deleteCustomer(@PathVariable(value = "id") Long id) {
        customerService.deleteById(id);
    }
}


