package com.example.demo.Services;

import com.example.demo.Models.Category;
import com.example.demo.Repo.categoryRepo;
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
    categoryRepo CategoryRepo;

    @InjectMocks
    categoryService CategoryService;

    @Test
    @Order(1)
    void getCategory() {
        ArrayList<Category> list = new ArrayList<>();
        Category category = new Category(1,"Test","Hi Iam test");
        Category category1 = new Category(2,"Test1","Hi Iam test1");
        list.add(category);
        list.add(category1);
        //System.out.println(category.getCategoryId());

        when(CategoryRepo.findAll()).thenReturn(list);
        assertEquals(list.size(),CategoryService.getCategory().size());

    }

    @Test
    @Order(2)
    void getOneCategory() {
        Category category = new Category(3,"Test","Hi Iam test");

        when(CategoryRepo.findById(any())).thenReturn(Optional.of(category));

        assertEquals(category.getCategoryName(),CategoryService.getOneCategory(3).getCategoryName());
    }
//optional.of() contains not null object
    @Test
    @Order(3)
    void updateCategory() {
        Category category = new Category(4,"Test","Hi Iam test");
        when(CategoryRepo.findById(any())).thenReturn(Optional.of(category));
        when(CategoryRepo.save(any())).thenReturn(category);
        Category actualCategory = CategoryService.updateCategory(4,category);
        assertEquals(category, actualCategory);

    }



    @Test
    @Order(4)
    void deleteCategory() {
        Category category = new Category(4,"Test","Hi Iam test");
          when(CategoryRepo.findById(any())).thenReturn(Optional.of(category));
          CategoryService.deleteCategory(category.getCategoryId());
          verify(CategoryRepo,times(1)).deleteById(category.getCategoryId());
    }
    //deletebyid repo ko change krega....or deletebyid kitni bar call horha hai overall

    @Test
    @Order(5)
    void saveCategory() {
        Category category = new Category(5,"Test","Hi Iam test");
        when(CategoryRepo.save(any())).thenReturn(category);
        Category category1 = CategoryService.saveCategory(category);
        assertEquals(category,category1);
    }
}