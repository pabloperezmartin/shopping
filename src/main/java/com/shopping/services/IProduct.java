package com.shopping.services;

import com.shopping.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProduct {
    public List<Product> getAllProducts();
    public Product getProductById(String Id);
    public void addProduct(Product product);
    public void editProduct(String Id, Product product);
    public void deleteProduct(String Id);
}
