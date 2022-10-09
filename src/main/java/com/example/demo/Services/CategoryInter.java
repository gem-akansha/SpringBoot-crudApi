package com.example.demo.Services;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Models.Category;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface CategoryInter {
    public List<Category> getCategory();

    public CategoryDTO saveCategory(CategoryDTO category);


    public CategoryDTO getCategoryById(Integer id);

    public CategoryDTO updateCategory(Integer id,CategoryDTO categoryDTO );

    String deleteCategory(Integer id);
}
