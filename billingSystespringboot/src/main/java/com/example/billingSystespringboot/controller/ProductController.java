package com.example.billingSystespringboot.controller;
import com.example.billingSystespringboot.model.Product;
import com.example.billingSystespringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public String addProduct(@RequestBody Product product) {
        try {
            productService.addProduct(product);
            return "Product added successfully";
        } catch (Exception e) {  // Catch all exceptions
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Product> getAllProducts() throws Exception {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable("id") int productId, @RequestBody Product product) {
        try {
            productService.updateProduct(productId, product);
            return "Product updated successfully";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") int productId) {
        try {
            productService.deleteProduct(productId);
            return "Product deleted successfully";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
