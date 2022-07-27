package com.example.demo.controller;

import com.example.demo.Models.Category;
import com.example.demo.Services.categoryInter;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Category Service", description = "Crud on category table")
@RestController
public class apiControllersCategory {

    @Autowired
    public categoryInter CategoryInter;


    @GetMapping(value = "/category")
    @Operation(summary = "Get Category", description = "Get list of all category")
    public List<Category> getCategory(){
        return CategoryInter.getCategory();
    }


    @GetMapping(value = "/category/{id}")
//    @Operation(@ExternalDocumentation="")
//    @ExternalDocumentation("hii")
    @Operation(summary = "Get category by id", description = "Get category of given id")
    public ResponseEntity getOneCategory(@PathVariable Integer id){
        return new ResponseEntity(CategoryInter.getOneCategory(id), HttpStatus.OK);
    }


    @PostMapping(value = "/category")
    @Operation(summary = "Save Category", description = "Save category")
    public Category saveCategory(@RequestBody Category category){
        return CategoryInter.saveCategory(category);
    }


    @PutMapping(value = "/category/{id}")
    @Operation(summary = "Update Category", description = "Update category of given id")
    public ResponseEntity updateCategory(@PathVariable Integer id,@RequestBody Category category ){
        return new ResponseEntity<>(CategoryInter.updateCategory(id,category),HttpStatus.OK);
    }

    @DeleteMapping(value = "/category/{id}")
    @Operation(summary = "Delete Category", description = "delete category of given id")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        return new ResponseEntity<>(CategoryInter.deleteCategory(id),HttpStatus.OK);
    }
}
