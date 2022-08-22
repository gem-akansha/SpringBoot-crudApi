package com.example.demo.controller;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Models.Products;
import com.example.demo.Services.ProductInter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@Tag(name = "Product Service", description = "Crud on product table")
public class ApiControllersProduct {
    @Autowired
    ProductInter productInter;
    Logger log = LoggerFactory.getLogger(ApiControllersProduct.class);


    /**
     *
     * @return
     */
    @GetMapping(value = "/")
    @Operation(summary = "Welocme", description = "Welocme Page")
    public String getPage(){
        log.info("Welcome Page Loaded");
        log.trace("Testing Trace");
        return "Welcome";
    }


    /**
     *
     * @param id
     * @return
     */
     @GetMapping(value = "/products")
    @Operation(summary = "Get Products", description = "Get list of all products")
    public ResponseEntity<?> mergeFindByIdAndFindAll(@RequestParam(required = false) Integer id){
        if(id!=null){
                log.info("Getting product of given id");
                return new ResponseEntity<>(productInter.getProductById(id), HttpStatus.FOUND);
        }
        else {
            log.info("Getting list of all products");
            return new ResponseEntity<>(productInter.getProduct(), HttpStatus.OK);
        }

    }


    /**
     *
     * @param productDTO
     * @return
     */
    @PostMapping(value = "/products")
    @Operation(summary = "Save Product", description = "Save product")
    public ResponseEntity saveProduct(@RequestBody ProductDTO productDTO){
       try{
           log.info("Adding product into db");
           return new ResponseEntity<>(productInter.saveProduct(productDTO),HttpStatus.OK);
       }
       catch (Exception e){
           log.error("Exception occured:{}",e.getMessage());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }


    /**
     *
     * @param id
     * @param productDTO
     * @return
     */
    @PutMapping(value = "/products/{id}")
    @Operation(summary = "Update Product", description =" Update product of given id")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO){
        return new ResponseEntity(productInter.updateProduct(id,productDTO),HttpStatus.OK);
    }


    /**
     *
     * @param id
     * @return
     */

    @DeleteMapping(value = "/products/{id}")
    @Operation(summary = "Delete Product", description = "delete product of given id")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        return new ResponseEntity(productInter.deleteProduct(id),HttpStatus.OK) ;
    }


    /**
     *Fetch Product details of all products whose name starts with "A"
     * @return
     */
    @GetMapping(value = "/products/A")
    public ResponseEntity getProduct_A(){
        try{
            return new ResponseEntity<>(productInter.getProduct_A(),HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Exception occured:{}",e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     *
     * @param id
     * @param name
     * @return
     */
    @GetMapping(value = "/products/update/{id}/{name}")
    public ResponseEntity updateName(@PathVariable("id") Integer id,@PathVariable(value = "name") String name){

        if(id!=null){
            return new ResponseEntity<>(productInter.updateName(id,name),HttpStatus.OK);
        }
        else {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/products/softDelete/{id}")
    public ResponseEntity softDelete(@PathVariable Integer id){
        return new ResponseEntity<>(productInter.softDelete(id),HttpStatus.OK);
    }

}
