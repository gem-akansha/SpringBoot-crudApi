package com.example.demo.Services;

import com.example.demo.Exception.IdNotFoundException;
import com.example.demo.Models.Category;
import com.example.demo.Repo.categoryRepo;
import com.example.demo.controller.apiControllersProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class categoryService implements categoryInter{
    @Autowired
    public categoryRepo CategoryRepo;

    Logger log = LoggerFactory.getLogger(apiControllersProduct.class);

    @Override
    public List<Category> getCategory() {
        log.info("Retrieving all Category");
        return CategoryRepo.findAll();
    }



    @Override
    public Category getOneCategory(Integer id) throws IdNotFoundException {
        Category category =null;

        if(CategoryRepo.findById(id).isEmpty()){
            log.error("Id not found exception");
            throw new IdNotFoundException();
        }
        else{
             log.info("Retrieving category for given id");
             category = CategoryRepo.findById(id).get();
             return category;
        }

    }

    @Override
    public Category updateCategory(Integer id, Category category) throws IdNotFoundException {
        if(CategoryRepo.findById(id).isEmpty()){
            log.error("Id not found exception");
            throw new IdNotFoundException();
        }
        else {
            Category updatedCategory = CategoryRepo.findById(id).get();
            updatedCategory.setCategoryName(category.getCategoryName());
            updatedCategory.setCategoryDescription(category.getCategoryDescription());
            // updatedCategory.setCreateDate(category.getCreateDate());
            updatedCategory.setUpdateDate(category.getUpdateDate());
            updatedCategory.setActive(category.getActive());
            updatedCategory.setDeleted(category.getDeleted());
            CategoryRepo.save(updatedCategory);
            log.info("Product Updated");
            return updatedCategory;
        }

    }

    @Override
    public String deleteCategory(Integer id) throws IdNotFoundException {
        if(CategoryRepo.findById(id).isEmpty()){
            log.error("Id not found exception");
            throw new IdNotFoundException();
        }
        else {
            Category category = CategoryRepo.findById(id).get();
            CategoryRepo.deleteById(category.getCategoryId());
            log.info("Category Deleted");
            return "Category is deleted with id :" + id;
        }

    }


    @Override
    public Category saveCategory(Category category) {
        Category savedCategory = CategoryRepo.save(category);
        log.info("Adding new product to table");
        return savedCategory;
    }

}
