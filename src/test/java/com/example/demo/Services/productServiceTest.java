package com.example.demo.Services;

import com.example.demo.Models.Products;
import com.example.demo.Repo.productRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class productServiceTest {

    @Autowired
    productInter ProductInter;

    @Autowired
    productRepo ProductRepo;


    @Test
    void testGetAllProduct(){
        List<Products> list = ProductRepo.findAll();
        assertThat(list.size()>0);
    }

    @Test
    void testGetOneProduct(){
        Products product = ProductRepo.findById(14).get();
        assertThat("snacks").isEqualTo(product.getProductName());
    }

    @Test
    void testSaveProduct(){
        Products product = new Products();
        product.setProductName("Testing");
        product.setProductDescription("Running the test");
        product.setPrice(879);
        ProductRepo.save(product);
        int id = product.getProductId();
        Products products = ProductRepo.findById(id).get();
        assertNotNull(products);
    }

//    @Test
//    void testDeleteProduct(){
//        Products product = ProductRepo.findById(14).get();
//        assertNotNull(product);
//        ProductRepo.deleteById(14);
//        Mockito.verify(ProductRepo,Mockito.times(1)).deleteById(14);
////        product = ProductRepo.findById(20).get();
//
//    }

    @Test
    void testUpdateProduct(){
        Products product = ProductRepo.findById(23).get();
        product.setProductName("New");
        product.setProductDescription("id");

        assertThat(product.getProductName()).isEqualTo("New");
    }


}