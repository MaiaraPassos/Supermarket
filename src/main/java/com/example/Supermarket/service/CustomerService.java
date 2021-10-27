package com.example.Supermarket.service;

import com.example.Supermarket.model.Customer;
import com.example.Supermarket.model.Purchase;
import com.example.Supermarket.repository.CustomerRepository;
import com.example.Supermarket.repository.PurchaseRepository;
import com.example.Supermarket.resquest.CustomerCreationRQ;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepo;
    private final PurchaseRepository purchaseRepo;

    public CustomerService(CustomerRepository customerRepo, PurchaseRepository purchaseRepo) {
        this.customerRepo = customerRepo;
        this.purchaseRepo = purchaseRepo;
    }
    public Customer CustomerNotFound(Long aLong, Object CustomerNotFound) {
        return customerRepo.findById(aLong).orElseThrow(CustomerNotFound::new);
    }
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public void deleteById(Long aLong) {
        Customer customerToDelete = this.CustomerNotFound(aLong, CustomerNotFound());
        for (Purchase purchase : customerToDelete.getPurchases()) {
            purchaseRepo.deleteById(purchase.getId());
        }
        customerRepo.deleteById(aLong);
    }
    public Customer update(CustomerCreationRQ newCustomerReq, Long customerId) {
        Customer customer = this.CustomerNotFound(customerId, CustomerNotFound();
        customer.setName(newCustomerReq.getName());
        customer.setAge(newCustomerReq.getAge());
        return customerRepo.save(customer);
    }
}
