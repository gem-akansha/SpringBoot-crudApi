package com.example.demo.services;

import com.example.demo.Models.Category;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface categoryInter {
    public List<Category> getCategory();

    public String saveCategory(Category category);

    public Category getOneCategory(Integer id);

    public String updateCategory(Integer id,Category category );

    String deleteCategory(Integer id);
}
