package com.example.Supermarket.service;

import com.example.Supermarket.model.Customer;
import com.example.Supermarket.model.Product;
import com.example.Supermarket.model.Purchase;
import com.example.Supermarket.repository.ProductRepository;
import com.example.Supermarket.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service

public class ProductService {
    private final ProductRepository productRepo;
    private final PurchaseRepository purchaseRepo;

    public ProductService(ProductRepository productRepo, PurchaseRepository purchaseRepo) {
        this.productRepo = productRepo;
        this.purchaseRepo = purchaseRepo;
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public List<Product> PurchaseNotFound(Long purchaseId, Object PurchaseNotFound) {
        List<Product> products = new ArrayList<>();
        Purchase purchase = purchaseRepo.findById(purchaseId).orElseThrow(PurchaseNotFound::new);
        //Populate products list with all products from the found Purchase object
        products.addAll(purchase.getPurchase_product());
        return products;
    }

    public Product ProductNotFound(Long aLong, Object ProductNotFound) {
        return productRepo.findById(aLong).orElseThrow(ProductNotFound::new);
    }

    public Product save(String description, Float price) {
        return productRepo.save(Product.builder().description(description).price(price).build());
    }
    public Product update(Long id, String description, float price) {
        Product product = this.ProductNotFound(id, ProductNotFound());
        product.setDescription(description);
        product.setPrice(price);
        return productRepo.save(product);
    }
    public void deleteById(Long aLong) {
        Long productId = this.ProductNotFound(aLong, ProductNotFound()).getId();
        //Find all purchases
        List<Customer> purchases = purchaseRepo.findAll();
        for (Customer purchase : purchases) { //Cycle through all purchases
            for (Product product : purchase.getPurchase_product()) { //Cycle through all products inside purchase
                if (Objects.equals(product.getId(), productId)) {
                    purchase.getPurchase_product().(remove = product);
                    purchaseRepo.save(purchase);
                }
            }
        }
        productRepo.deleteById(aLong);
    }

}