package com.example.demo.Services;

import com.example.demo.Models.Products;
import com.example.demo.Repo.productRepo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class productServiceTest {
    @Mock
    productRepo ProductRepo;

    @InjectMocks
    productService ProductService;

    @Test
    @Order(1)
    void getProduct() {
        ArrayList<Products> list = new ArrayList<>();
        Products product = new Products(1,"Product Test","Testing Product",780,12);
        Products product1 = new Products(2,"Product Test1","Testing Product1",780,14);
        list.add(product);
        list.add(product1);
        when(ProductRepo.findAll()).thenReturn(list);
        assertEquals(2,ProductService.getProduct().size());
    }

    @Test
    @Order(2)
    void getOneProduct() {
        Products product = new Products(1,"Product Test","Testing Product",780,12);
        when(ProductRepo.findById(any())).thenReturn(Optional.of(product));
        assertEquals(product.getProductName(),ProductService.getOneProduct(1).getProductName());
    }

    @Test
    @Order(3)
    void saveProduct() {
        Products product = new Products(1,"Product Test","Testing Product",780,12);
        ProductService.saveProduct(product);
        verify(ProductRepo,times(1)).save(product);
    }

    @Test
    @Order(4)
    void updateProduct() {
        Products product = new Products(1,"Product Test","Testing Product",780,12);
        when(ProductRepo.findById(any())).thenReturn(Optional.of(product));
        when(ProductRepo.save(any())).thenReturn(product);
        Products actualProduct = ProductService.updateProduct(1,product);
        verify(ProductRepo,times(2)).findById(1);
        verify(ProductRepo,times(1)).save(product);

    }

    @Test
    @Order(5)
    void deleteProduct() {
        Products product = new Products(1,"Product Test","Testing Product",780,12);
        when(ProductRepo.findById(any())).thenReturn(Optional.of(product));
        ProductService.deleteProduct(1);
        verify(ProductRepo,times(1)).delete(product);

    }
}