package com.example.demo.controller;

import com.example.demo.Models.Products;
import com.example.demo.Services.productInter;
import com.example.demo.Services.productService;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class apiControllersProductTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    productInter ProductInter;

    @InjectMocks
    apiControllersProduct ApiControllersProduct;
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(ApiControllersProduct).build();
    }

    @Test
    @Order(1)
    void mergeFindByIdAndFindAll() throws Exception {
        List<Products> list = new ArrayList<>();
        Products product = new Products(1,"Test","Hi am test",45,4);
        Products product1 = new Products(2,"Testing","Hi am test",45,4);
        list.add(product);
        list.add(product1);
        when(ProductInter.getOneProduct(any())).thenReturn(product);
        when(ProductInter.getProduct()).thenReturn(list);

        this.mockMvc
                .perform(get("/products"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void saveProduct() throws Exception {
        Products product = new Products(1,"Test","Hi am test",45,4);
        when(ProductInter.saveProduct(any())).thenReturn("Saved.....");

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(product);

        this.mockMvc
                .perform(post("/products")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               // .andExpect(MockMvcResultMatchers.jsonPath(".productId").value(1))
                .andDo(print());


    }

    @Test
    void updateProduct() throws Exception {
        Products product = new Products(1,"Test","Hi am test",45,4);
        int id = 1;
        when(ProductInter.updateProduct(any(),any())).thenReturn(product);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(product);

        this.mockMvc
                .perform(put("/products/{id}",id)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".productName").value("Test"))
                .andDo(print());
    }

    @Test
    void deleteProduct() throws Exception {
        Products product = new Products(1,"Test","Hi am test",45,4);
        when(ProductInter.deleteProduct(any())).thenReturn("Product Deleted");

        this.mockMvc
                .perform(delete("/products/{id}",1))
                .andExpect(status().isOk())
                .andDo(print());
    }
}