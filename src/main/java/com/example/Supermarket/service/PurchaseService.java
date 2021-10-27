package com.example.Supermarket.service;

import com.example.Supermarket.model.Customer;
import com.example.Supermarket.model.Product;
import com.example.Supermarket.model.Purchase;
import com.example.Supermarket.repository.CustomerRepository;
import com.example.Supermarket.repository.ProductRepository;
import com.example.Supermarket.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepo;
    private final CustomerRepository customerRepo;
    private final ProductRepository productRepository;
    private Object PurchaseNotFound;

    public PurchaseService(PurchaseRepository purchaseRepo, CustomerRepository customerRepo, ProductRepository productRepository) {
        this.purchaseRepo = purchaseRepo;
        this.customerRepo = customerRepo;
        this.productRepository = productRepository;
    }

    public List<Customer> findAll() {
        return purchaseRepo.findAll();
    }
    public Purchase findById(Long id) {
        return purchaseRepo.findById(id).orElseThrow(PurchaseNotFound::new);
    }
    public Purchase save(Boolean isPaid, Long customerId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(CustomerNotFound::new);
        Purchase purchase = new Purchase();
        purchase.setIsPaid(isPaid);
        purchase.setCustomer(customer);
        return purchaseRepo.save(purchase);
    }
    public Purchase ProductNotFound(Long productId, Long purchaseId, Object ProductNotFound) {
        Purchase purchase = this.findById(purchaseId);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFound::new);
        purchase.getPurchase_product().add(product);
        return purchaseRepo.save(purchase);
    }
    public void deleteById(Long id) {
        Purchase purchase = this.findById(id);
    }
}
