package com.example.demo.Services;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Exception.IdNotFoundException;
import com.example.demo.Models.Category;
import com.example.demo.Repo.CategoryRepo;
import com.example.demo.controller.ApiControllersProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class CategoryService implements CategoryInter {
    @Autowired
    CategoryRepo categoryRepo;

    Logger log = LoggerFactory.getLogger(ApiControllersProduct.class);

    /**
     *
     * @return
     */
    @Override
    @Cacheable(value = "category")
    public List<Category> getCategory() {
        log.info("Retrieving all Category");
        return categoryRepo.findAll();
    }


    /**
     *
     * @param id
     * @return
     * @throws IdNotFoundException
     */
    @Override
    @Cacheable(value = "category",key="#id")
    public CategoryDTO getCategoryById(Integer id) throws IdNotFoundException {
        Category category =null;

        if(categoryRepo.findById(id).isEmpty()){
            log.error("Id not found exception");
            throw new IdNotFoundException();
        }
        else{
             log.info("Retrieving category for given id");
             category = categoryRepo.findById(id).get();
             CategoryDTO categoryDTO = convertEntityToDto(category);
             return categoryDTO;
        }

    }


    /**
     *
     * @param id
     * @param categoryDTO
     * @return
     * @throws IdNotFoundException
     */
    @Override
    @CachePut(value = "category")
    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) throws IdNotFoundException {
        if(categoryRepo.findById(id).isEmpty()){
            log.error("Id not found exception");
            throw new IdNotFoundException();
        }
        else {
             Category updatedCategory = categoryRepo.findById(id).get();
            updatedCategory.setCategoryName(categoryDTO.getCategoryName());
            updatedCategory.setCategoryDescription(categoryDTO.getCategoryDescription());
            updatedCategory.setUpdateDate(LocalDate.now());
            categoryRepo.save(updatedCategory);
            CategoryDTO updatedCategoryDto = convertEntityToDto(updatedCategory);
            log.info("Product Updated");
            return updatedCategoryDto;
        }

    }


    /**
     *
     * @param id
     * @return
     * @throws IdNotFoundException
     */
    @Override
    @CacheEvict(value = "category", allEntries = true)
    public String deleteCategory(Integer id) throws IdNotFoundException {
        if(categoryRepo.findById(id).isEmpty()){
            log.error("Id not found exception");
            throw new IdNotFoundException();
        }
        else {
            Category category = categoryRepo.findById(id).get();
            categoryRepo.deleteById(category.getCategoryId());
            log.info("Category Deleted");
            return "Category Deleted";
        }

    }


    /**
     *
     * @param categoryDTO
     * @return
     */
    @Override
    @CacheEvict(value = "category", allEntries = true)
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = convertDtoToEntity(categoryDTO);
        Category savedCategory = categoryRepo.save(category);
        CategoryDTO savedCategoryDto = convertEntityToDto(savedCategory);
        log.info("Adding new product to table");
        return savedCategoryDto;
    }


    /**
     *
     * @param category
     * @return
     */
    public CategoryDTO convertEntityToDto(Category category){
    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setCategoryName(category.getCategoryName());
    categoryDTO.setCategoryDescription(category.getCategoryDescription());
    return categoryDTO;
}

    /**
     *
     * @param categoryDTO
     * @return
     */
    public Category convertDtoToEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        return category;
    }

}
