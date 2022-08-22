package com.example.demo.Services;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Models.Products;
import com.example.demo.Repo.ProductRepo;
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
    ProductRepo productRepo;

    @InjectMocks
    ProductService productService;

    @Test
    @Order(1)
    void getProduct() {
        ArrayList<Products> list = new ArrayList<>();
        Products product = new Products(1,"Product Test","Testing Product",780,12);
        Products product1 = new Products(2,"Product Test1","Testing Product1",780,14);
        list.add(product);
        list.add(product1);
        when(productRepo.findAll()).thenReturn(list);
        assertEquals(2, productService.getProduct().size());
    }

    @Test
    @Order(2)
    void getOneProduct() {
        Products product = new Products(1,"Product Test","Testing Product",780,12);
        when(productRepo.findById(any())).thenReturn(Optional.of(product));
        assertEquals(product.getProductName(), productService.getProductById(1).getProductName());
    }

    @Test
    @Order(3)
    void saveProduct() {
        ProductDTO productDTO = new ProductDTO("Product Test", "Testing Product", 780, 12);
        Products product = new Products(1, "Product Test", "Testing Product", 780, 12);
        when(productRepo.save(any())).thenReturn(product);
        ProductDTO productDTO1 = productService.saveProduct(productDTO);
        assertEquals(productDTO1.getProductName(), product.getProductName());
        //verify(productRepo,times(1)).save(product);
    }

    @Test
    @Order(4)
    void updateProduct() {
        ProductDTO productDTO =new ProductDTO("Product Test","Testing Product",780,12);
        Products product = new Products(1,"Product Test","Testing Product",780,12);
        when(productRepo.findById(any())).thenReturn(Optional.of(product));
        when(productRepo.save(any())).thenReturn(product);
        ProductDTO productDTO1 = productService.updateProduct(1,productDTO);
        verify(productRepo,times(2)).findById(1);
        verify(productRepo,times(1)).save(product);

    }

    @Test
    @Order(5)
    void deleteProduct() {
        Products product = new Products(1,"Product Test","Testing Product",780,12);
        when(productRepo.findById(any())).thenReturn(Optional.of(product));
        productService.deleteProduct(1);
        verify(productRepo,times(1)).delete(product);

    }
}