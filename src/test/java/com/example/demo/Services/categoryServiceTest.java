package com.example.demo.Services;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Models.Category;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class categoryServiceTest {

    @Mock
    com.example.demo.Repo.CategoryRepo categoryRepo;

    @InjectMocks
    com.example.demo.Services.CategoryService categoryService;

    @Test
    @Order(1)
    void getCategory() {
        ArrayList<Category> list = new ArrayList<>();
        Category category = new Category(1,"Test","Hi Iam test");
        Category category1 = new Category(2,"Test1","Hi Iam test1");
        list.add(category);
        list.add(category1);
        //System.out.println(category.getCategoryId());

        when(categoryRepo.findAll()).thenReturn(list);
        assertEquals(list.size(), categoryService.getCategory().size());

    }

    @Test
    @Order(2)
    void getCategoryById() {
        Category category = new Category(3,"Test","Hi Iam test");

        when(categoryRepo.findById(any())).thenReturn(Optional.of(category));

        assertEquals(category.getCategoryName(), categoryService.getCategoryById(3).getCategoryName());
    }
//optional.of() contains not null object
    @Test
    @Order(3)
    void updateCategory() {
        CategoryDTO categoryDTO=new CategoryDTO("Test","Hi Iam test");
        Category category = new Category(4,"Test","Hi Iam test");
        when(categoryRepo.findById(any())).thenReturn(Optional.of(category));
        when(categoryRepo.save(any())).thenReturn(category);
        CategoryDTO categoryDTO1 = categoryService.updateCategory(4,categoryDTO);
        assertEquals(category.getCategoryName(), categoryDTO1.getCategoryName());

    }



    @Test
    @Order(4)
    void deleteCategory() {
        Category category = new Category(4,"Test","Hi Iam test");
          when(categoryRepo.findById(any())).thenReturn(Optional.of(category));
          categoryService.deleteCategory(category.getCategoryId());
           categoryService.deleteCategory(category.getCategoryId());
          verify(categoryRepo,times(2)).deleteById(category.getCategoryId());
    }
    //deletebyid repo ko change krega....or deletebyid kitni bar call horha hai overall

    @Test
    @Order(5)
    void saveCategory() {
        CategoryDTO categoryDTO = new CategoryDTO("Test","Hi Iam test");
        Category category = new Category(1,"Test","Hi Iam test");
        when(categoryRepo.save(any())).thenReturn(category);
        CategoryDTO categoryDTO1 = categoryService.saveCategory(categoryDTO);
        assertEquals(category.getCategoryName(),categoryDTO1.getCategoryName());
    }
}