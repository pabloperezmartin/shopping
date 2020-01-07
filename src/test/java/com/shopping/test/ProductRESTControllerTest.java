package com.shopping.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.controllers.ProductRESTController;
import com.shopping.entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductRESTController.class)
public class ProductRESTControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllProducts() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/product-rest/products")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].id").isNotEmpty());
    }

    @Test
    public void getProductById() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/product-rest/products/{id}", "A001")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("A001"));
    }

    @Test
    public void createProduct() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .post("/product-rest/products/add")
                .content(asJsonString(new Product("A004", "Product 4", "Product Description 4", "gif4.png", 99.99)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void updateProduct() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .put("/product-rest/products/edit/{id}", "A002")
                .content(asJsonString(new Product("A002", "Product 2", "New Product Description 2", "gif2.png", 10.99)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("A002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Product Description 2"));
    }
    @Test
    public void deleteProduct() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders.delete("/product-rest/products/delete/{id}", "A003") )
                .andExpect(status().isAccepted());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
