package com.shopping.test;

import com.shopping.controllers.CartController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CartController.class)
public class CartControllerTest {

    private static final Object PRODUCT_ID = "A001";

    @Autowired
    MockMvc mockMvc;

    @Captor
    ArgumentCaptor<String> produtCaptor;

    @BeforeEach
    void setUp() {
    }

    @Test
    void index() {
    }

    @Test
    public void buy() throws Exception {

        final ResultActions resultActions = mockMvc.perform(get(String.format("/cart/buy/%s", PRODUCT_ID))).andExpect(status().isOk());
        assertNotNull(resultActions);
        assertEquals(PRODUCT_ID, produtCaptor.getValue());
    }

    @Test
    void remove() {
    }
}