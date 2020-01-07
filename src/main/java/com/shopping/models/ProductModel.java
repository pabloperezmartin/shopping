package com.shopping.models;

import com.shopping.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {

    private List<Product> products;

    public ProductModel() {
        this.products = new ArrayList<Product>();
        this.products.add(new Product("A001", "Product 1", "Product Description 1", "gif1.png", 32.45));
        this.products.add(new Product("A002", "Product 2", "Product Description 2", "gif2.png", 12.89));
        this.products.add(new Product("A003", "Product 3", "Product Description 3", "gif3.png", 10.15));
    }

    public List<Product> findAll() {
        return this.products;
    }

    public Product find(String id) {
        for (Product product : this.products) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }
}
