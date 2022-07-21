package com.example.demo.services;

import com.example.demo.Models.Products;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface productInter {
    public List<Products> getProduct();
    public Products getOneProduct( Integer id);
    public String saveProduct(Products product);
    public String updateProduct(Integer id,Products product);
    public String deleteProduct(Integer id);

    public String updateSelectedProduct(Integer id, Products product);
}
