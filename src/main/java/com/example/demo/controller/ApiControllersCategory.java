package com.example.demo.controller;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Services.CategoryInter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
@Slf4j
@Tag(name = "Category Service", description = "Crud on category table")
@RestController

public class ApiControllersCategory {
    @Autowired
    CategoryInter categoryInter;


    /**
     *
     * @return
     */
    @GetMapping(value = "/category")
    @Operation(summary = "Get Category", description = "Get list of all category")
    public ResponseEntity<?> getCategory(){
        try{
            log.info("Getting List of all category");
            return new ResponseEntity<>(categoryInter.getCategory(),HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Exception occured: {}",e.getMessage());
             return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/category/{id}")
    @Operation(summary = "Get category by id", description = "Get category of given id")
    public ResponseEntity getCategoryById(@PathVariable Integer id){
        log.info("Getting category by id");
        return new ResponseEntity(categoryInter.getCategoryById(id), HttpStatus.OK);
    }


    /**
     *
     * @param categoryDTO
     * @return
     */
    @PostMapping(value = "/category")
    @Operation(summary = "Save Category", description = "Save category")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categoryDTO){
        try{
            log.info("Saving category into db");
            return new ResponseEntity<>(categoryInter.saveCategory(categoryDTO),HttpStatus.CREATED);
        }
       catch (Exception e){
           log.error("Exception occured: {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }


    /**
     *
     * @param id
     * @param categoryDTO
     * @return
     */
    @PutMapping(value = "/category/{id}")
    @Operation(summary = "Update Category", description = "Update category of given id")
    public ResponseEntity updateCategory(@PathVariable Integer id,@RequestBody CategoryDTO categoryDTO ){
        log.info("Updating category of given id");
        return new ResponseEntity<>(categoryInter.updateCategory(id,categoryDTO),HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/category/{id}")
    @Operation(summary = "Delete Category", description = "delete category of given id")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        log.info("Deleting category by id");
        return new ResponseEntity<>(categoryInter.deleteCategory(id),HttpStatus.OK);
    }


}
