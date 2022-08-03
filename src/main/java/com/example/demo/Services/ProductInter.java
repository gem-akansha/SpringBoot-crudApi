package com.example.demo.Services;

import com.example.demo.Models.Products;

import java.util.List;

public interface ProductInter {
    public List<Products> getProduct();
    public Products getOneProduct( Integer id);
    public String saveProduct(Products product);
    public Products updateProduct(Integer id,Products product);
    public String deleteProduct(Integer id);

     public List<Products> getProduct_A();

    public Products updateName( Integer id,String name);

    public String softDelete(Integer id);

    // public String updateSelectedProduct(Integer id, Products product);
}
