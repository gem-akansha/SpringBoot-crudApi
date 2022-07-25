package com.example.demo.Services;

import com.example.demo.Models.Products;
import com.example.demo.Repo.productRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest

//@RunWith(MockitoJUnitRunner.class)
public class productServiceTestMockito {



    @Mock
    productInter ProductInter;





    @Test
    void testDeleteProduct(){
//        Products product = ProductRepo.findById(14).get();
//        assertNotNull(product);
//        ProductRepo.deleteById(14);
//        Mockito.verify(ProductRepo,Mockito.times(1)).deleteById(14);
////        product = ProductRepo.findById(20).get();

        when(ProductInter.deleteProduct(14)).thenReturn("User is deleted with id 14");

        String str  = ProductInter.deleteProduct(14);
        assertThat(ProductInter.deleteProduct(14)).isEqualTo(str);
        Mockito.verify(ProductInter,times(2)).deleteProduct(14);

    }

    @Test
    void testSaveProduct(){
        Products product = new Products();
        product.setProductName("Testing");
        product.setProductDescription("Running the test");
        product.setPrice(879);
//        ProductRepo.save(product);
//        int id = product.getProductId();
//        Products products = ProductRepo.findById(id).get();
//        assertNotNull(products);

        when(ProductInter.saveProduct(product)).thenReturn("Saved.....");

        String str = ProductInter.saveProduct(product);
        assertThat(ProductInter.saveProduct(product)).isEqualTo(str);
        verify(ProductInter, times(2)).saveProduct(product);
    }

}
