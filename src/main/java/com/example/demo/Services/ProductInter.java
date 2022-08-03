package com.example.demo.Services;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Models.Products;

import java.util.List;

public interface ProductInter {
    public List<Products> getProduct();
    public Products getProductById( Integer id);
    public ProductDTO saveProduct(ProductDTO productDTO);
    public ProductDTO updateProduct(Integer id,ProductDTO productDTO);
    public String deleteProduct(Integer id);

     public List<Products> getProduct_A();

    public Products updateName( Integer id,String name);

    public String softDelete(Integer id);

}
