package com.shopping.services;

import com.shopping.entities.Product;
import com.shopping.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProduct {

    //@Autowired
    //ProductRepository repository;

    @Override
    public List<Product> getAllProducts() {
        ProductRepository repository = new ProductRepository();
        return repository.getAllProducts();
    }

    @Override
    public Product getProductById(String Id) {
        ProductRepository repository = new ProductRepository();
        return repository.getProductById(Id);
    }

    @Override
    public void addProduct(Product product) {
        ProductRepository repository = new ProductRepository();
        repository.addProduct(product);
    }

    @Override
    public void editProduct(String Id, Product product) {
        ProductRepository repository = new ProductRepository();
        repository.editProduct(Id, product);
    }

    @Override
    public void deleteProduct(String Id) {
        ProductRepository repository = new ProductRepository();
        repository.deleteProduct(Id);
    }


}
