package com.shopping.repositories;

import com.shopping.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products;

    private void initialize() {

        this.products = new ArrayList<Product>();
        this.products.add(new Product("A001", "Product 1", "Product Description 1", "gif1.png", 32.45));
        this.products.add(new Product("A002", "Product 2", "Product Description 2", "gif2.png", 12.89));
        this.products.add(new Product("A003", "Product 3", "Product Description 3", "gif3.png", 10.15));

    }

    public List<Product> getAllProducts() {

        initialize();
        return this.products;
    }

    public Product getProductById(String Id) {
        initialize();
        for (Product product : this.products) {
            if (product.getId().equalsIgnoreCase(Id)) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        initialize();
        this.products.add(product);
    }

    public void editProduct(String Id, Product product) {
        initialize();
        for (Product product1 : this.products) {
            if (product1.getId().equalsIgnoreCase(Id)) {
                this.products.remove(product1);
            }
        }
        this.products.add(product);
    }

    public void deleteProduct(String Id) {
        initialize();
        for (Product product : this.products) {
            if (product.getId().equalsIgnoreCase(Id)) {
                this.products.remove(product);
            }
        }
    }
}

