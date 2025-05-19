package com.example.billingSystespringboot.service;

import com.example.billingSystespringboot.dao.ProductDAO;
import com.example.billingSystespringboot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public void addProduct(Product product) throws SQLException {
        productDAO.addProduct(product);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }
    public void updateProduct(int productId, Product product) throws SQLException {
        productDAO.updateProduct(productId, product);
    }

    public void deleteProduct(int productId) throws SQLException {
        productDAO.deleteProduct(productId);
    }
}
