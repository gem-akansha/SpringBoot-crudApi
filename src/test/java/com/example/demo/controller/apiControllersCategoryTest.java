package com.example.demo.controller;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Models.Category;

import com.example.demo.Services.CategoryInter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ComponentScan(basePackages = "com.example.demo")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class apiControllersCategoryTest {
    @Autowired
    MockMvc mockMvc;

    @Mock
    CategoryInter categoryInter;

    @InjectMocks
    ApiControllersCategory apiControllersCategory;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(apiControllersCategory).build();
    }

    @Test
    @Order(1)
    void getCategory() throws Exception {
        List<Category> list = new ArrayList<>();
        Category category = new Category(1,"Controller test","Testing Controller");
        Category category1 = new Category(2,"Controller test1","Testing Controller1");
        list.add(category);
        list.add(category1);

        when(categoryInter.getCategory()).thenReturn(list);
        this.mockMvc
                .perform(get("/category"))
                .andExpect(status().isOk())
                .andDo(print());


    }

    @Test
    @Order(2)
    void getCategoryById() throws Exception {
        Category category = new Category(1,"Controller test","Testing Controller");
        when(categoryInter.getCategoryById(any())).thenReturn(category);

        this.mockMvc
                .perform(get("/category/{id}",1))
                .andExpect(status().isOk())
                //MockMvcResultMatchers.jsonPath IS USED TO EXTRACT FIELDS OF JSON OBJECT
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryName").value("Controller test"))
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryDescription").value("Testing Controller"))
                .andDo(print());

    }

    @Test
    @Order(3)
    void saveCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO("Controller test","Testing Controller");
        when(categoryInter.saveCategory(any())).thenReturn(categoryDTO);



        //ObjectMapper is used to convert java object into json object
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //writeValueAsString is used to convert json object as string..
        //json object hi rhegA BUT AS STRING TREAT HOGA
        String jsonBody = mapper.writeValueAsString(categoryDTO);

        //String json =  "{\"categoryId\":1,\"categoryName\":\"Controller test\",\"categoryDescription\":\"Testing Controller\",\"createDate\":\"28-07-2022\",\"updateDate\":\"28-07-2022\",\"active\":true,\"deleted\":false}";
        this.mockMvc
                .perform(post("/category")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryName").value("Controller test"))
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryDescription").value("Testing Controller"))
                .andDo(print());
    }

    @Test
    @Order(4)
    void updateCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO("Controller test","Testing Controller");
        when(categoryInter.updateCategory(any(),any())).thenReturn(categoryDTO);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String jsonBody = mapper.writeValueAsString(categoryDTO);
        this.mockMvc
                .perform(put("/category/{id}",1)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryName").value("Controller test"))
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryDescription").value("Testing Controller"))
                .andDo(print());
    }

    @Test
    @Order(5)
    void deleteCategory() throws Exception {
        when(categoryInter.deleteCategory(any())).thenReturn("Category Deleted");
        this.mockMvc
                .perform(delete("/category/{id}",989))
                .andExpect(status().isOk())
                .andDo(print());
    }
}