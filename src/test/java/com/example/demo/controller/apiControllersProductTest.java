package com.example.demo.controller;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Models.Products;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
    com.example.demo.Services.ProductInter productInter;

    @InjectMocks
    com.example.demo.controller.ApiControllersProduct apiControllersProduct;
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(apiControllersProduct).build();
    }

    @Test
    @Order(1)
    void mergeFindByIdAndFindAll() throws Exception {
        List<Products> list = new ArrayList<>();
        Products product = new Products(1,"Test","Hi am test",45,4);
        Products product1 = new Products(2,"Testing","Hi am test",45,4);
        list.add(product);
        list.add(product1);
        when(productInter.getProductById(any())).thenReturn(product);
        when(productInter.getProduct()).thenReturn(list);

        this.mockMvc
                .perform(get("/products").param("id","1"))
                .andExpect(status().isFound())
                .andDo(print());

    }

    @Test
    void saveProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO("Test","Hi am test",45,4);
        when(productInter.saveProduct(any())).thenReturn(productDTO);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String jsonBody = mapper.writeValueAsString(productDTO);

        this.mockMvc
                .perform(post("/products")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".productName").value("Test"))
                .andExpect(MockMvcResultMatchers.jsonPath(".productDescription").value("Hi am test"))
                .andExpect(MockMvcResultMatchers.jsonPath(".price").value(45))
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryId").value(4))
                .andDo(print());


    }

    @Test
    void updateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO("Test","Hi am test",45,4);
        int id = 1;
        when(productInter.updateProduct(any(),any())).thenReturn(productDTO);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String jsonBody = mapper.writeValueAsString(productDTO);

        this.mockMvc
                .perform(put("/products/{id}",id)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".productName").value("Test"))
                .andExpect(MockMvcResultMatchers.jsonPath(".productDescription").value("Hi am test"))
                .andExpect(MockMvcResultMatchers.jsonPath(".price").value(45))
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryId").value(4))
                .andDo(print());
    }

    @Test
    void deleteProduct() throws Exception {
        Products product = new Products(1,"Test","Hi am test",45,4);
        when(productInter.deleteProduct(any())).thenReturn("Product Deleted");

        this.mockMvc
                .perform(delete("/products/{id}",1))
                .andExpect(status().isOk())
                .andDo(print());
    }
}