package com.shopping.controllers;

import com.shopping.entities.Product;
import com.shopping.services.ProductServiceImpl;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "product-rest")
public class ProductRESTController {

    //@Autowired
    //ProductServiceImpl productService;

    final static Logger logger = Logger.getLogger(ProductRESTController.class);

    @RequestMapping(value = "products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProducts()
    {
        try {
            ProductServiceImpl productService = new ProductServiceImpl();
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts()
            );
        } catch (Exception ex) {
            logger.error("An error occurred while getting All Products.", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById (@PathVariable("id") String id)
    {

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            ProductServiceImpl productService = new ProductServiceImpl();
            return ResponseEntity.status(HttpStatus.OK).body(
                    productService.getProductById(id)
            );
        } catch (Exception ex) {
            logger.error("An error occurred while getting a Product By An Id.", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "products/add")
    public ResponseEntity<Product> addProduct (@Valid @RequestBody Product product)
    {
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PutMapping(value = "products/edit/{id}")
    public ResponseEntity<Product> updateProduct (@PathVariable("id") String id, @Valid @RequestBody Product product)
    {
        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            ProductServiceImpl productService = new ProductServiceImpl();
            productService.editProduct(id, product);
            return ResponseEntity.status(HttpStatus.OK).body(
                    productService.getProductById(id)
            );
        } catch (Exception ex) {
            logger.error("An error occurred while updating a Product By An Id.", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "products/delete/{id}")
    public ResponseEntity<HttpStatus> removeProduct (@PathVariable("id") String id)
    {
        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            ProductServiceImpl productService = new ProductServiceImpl();
            productService.deleteProduct(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            logger.error("An error occurred while deleting a Product By An Id.", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
