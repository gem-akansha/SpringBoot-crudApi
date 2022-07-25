package com.example.demo.Services;

import com.example.demo.Models.Category;

import java.util.List;

public interface categoryInter {
    public List<Category> getCategory();

    public String saveCategory(Category category);

    public Category getOneCategory(Integer id);

    public String updateCategory(Integer id,Category category );

    String deleteCategory(Integer id);
}
