package com.example.demo.controller;

import com.example.demo.Models.Products;
import com.example.demo.Services.productInter;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@Api(tags = {"Product Service"}, description = "test")

public class apiControllersProduct {

    @Autowired
    private productInter ProductInter;

    Logger log = LoggerFactory.getLogger(apiControllersProduct.class);

//    @Autowired
//    private categoryRepo cr;


    @GetMapping(value = "/")
    public String getPage(){
        log.info("Welcome Page Loaded");
        log.trace("Testing Trace");
        return "Welcome";
    }


//    @GetMapping(value = "/products")
//    public List<Products> getProduct(){
//       // return ProductRepo.findAll();
//        return ProductInter.getProduct();
//    }
//
//    @GetMapping(value = "/products/{id}")
//    public Products getOneProduct(@PathVariable Integer id){
//        return ProductInter.getOneProduct(id);
//    }



    @GetMapping(value = "/products")
    public ResponseEntity<?> mergeFindByIdAndFindAll(@RequestParam(required = false) Integer id){
        if(id!=null){
//         try{
//            return new ResponseEntity<>(ProductInter.getOneProduct(id), HttpStatus.OK);
//        }
//        catch (IdNotFoundException e) {
//            return new ResponseEntity<>("NOT found",HttpStatus.INTERNAL_SERVER_ERROR);
//        }

                return new ResponseEntity<>(ProductInter.getOneProduct(id), HttpStatus.OK);


        }
        else {
            return new ResponseEntity<>(ProductInter.getProduct(), HttpStatus.OK);
        }



//

    }



    @PostMapping(value = "/products")
    public String saveProduct(@RequestBody Products product){
        return ProductInter.saveProduct(product);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Products product){
        return new ResponseEntity(ProductInter.updateProduct(id,product),HttpStatus.OK);
    }


    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        return new ResponseEntity(ProductInter.deleteProduct(id),HttpStatus.OK) ;
    }


    @PatchMapping(value = "/products/{id}")
    public ResponseEntity updateSelectedProduct(@PathVariable Integer id,@RequestBody Products product){
        return new ResponseEntity(ProductInter.updateSelectedProduct(id, product),HttpStatus.OK);
    }


}
