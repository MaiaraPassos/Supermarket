package com.example.Supermarket.controller;

import com.example.Supermarket.model.Product;
import com.example.Supermarket.response.ProductReturnResponse;
import com.example.Supermarket.resquest.ProductCreationRQ;
import com.example.Supermarket.service.ProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated

public class ProductController {
    private final ProductService productServ;

    public ProductController(ProductService productServ) {
        this.productServ = productServ;
    }

    //Get all products
    @GetMapping("/products")
    public List<ProductReturnResponse> getProducts() {
        List<Product> products = productServ.findAll();
        List<ProductReturnResponse> productsResp = new ArrayList<>();
        if (!products.isEmpty()) {
            for (Product product : products) {
                productsResp.add(new ProductReturnResponse()
                        .createProductReturnResponse(product));
            }
            return productsResp;
        }
        return Collections.emptyList();
    }
    //Get product by id
    @GetMapping("/products/{id}")
    public ProductReturnResponse getProductById(@PathVariable(value = "id") Long id) {
        Product product = productServ.findById(id);
        //Transforms the returned Product from productServ.findById() into a ProductReturnResponse and returns it
        return new ProductReturnResponse()
                .createProductReturnResponse(product);
    }

    //Get products by purchase
    @GetMapping("/products/purchases/{id}")
    public List<ProductReturnResponse> getAllProductsFromPurchase(@PathVariable(value = "id") Long purchaseId) {
        List<Product> products = productServ.getAllProductsFromPurchase(purchaseId);
        List<ProductReturnResponse> productsRetResp = new ArrayList<>();
        //Transforms the returned List<Product> from productServ.getAllProductsFromPurchase() into a List<ProductReturnResponse> and returns it
        if(!products.isEmpty()) {
            for (Product product : products) {
                productsRetResp.add(new ProductReturnResponse()
                        .createProductReturnResponse(product));
            }
            return productsRetResp;
        }
        return Collections.emptyList();
    }

    //Create product
    @PostMapping(value = "/products", consumes = "application/json", produces = "application/json")
    public ProductReturnResponse createProduct(@RequestBody ProductCreationRQ productReq) {
        Product newProduct = productServ.save(productReq.getDescription(), productReq.getPrice());
        //Transforms the returned Product from productServ.save() into a ProductReturnResponse and returns it
        return new ProductReturnResponse()
                .createProductReturnResponse(newProduct);
    }

    //Update product
    @PutMapping(value = "/products/{id}", consumes = "application/json", produces = "application/json")
    public ProductReturnResponse updateProduct(@PathVariable(value = "id") Long id, @RequestBody ProductCreationRQ productReq) {
        Product product = productServ.update(id, productReq.getDescription(), productReq.getPrice());
        return new ProductReturnResponse()
                .createProductReturnResponse(product);
    }

    //Delete product
    @DeleteMapping(value = "/products/{id}")
    public void deleteProduct(@PathVariable(value = "id") Long id) {
        productServ.deleteById(id);
    }
}

