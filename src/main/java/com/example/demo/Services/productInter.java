package com.example.demo.Services;

import com.example.demo.Models.Products;

import java.util.List;

public interface productInter {
    public List<Products> getProduct();
    public Products getOneProduct( Integer id);
    public String saveProduct(Products product);
    public Products updateProduct(Integer id,Products product);
    public String deleteProduct(Integer id);

   // public String updateSelectedProduct(Integer id, Products product);
}
