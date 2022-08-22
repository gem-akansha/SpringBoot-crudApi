package com.example.demo.Services;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Exception.IdNotFoundException;
import com.example.demo.Models.Products;

import com.example.demo.Repo.ProductRepo;
import com.example.demo.controller.ApiControllersProduct;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ProductService implements ProductInter {

    @Autowired
    ProductRepo productRepo;


    Logger log = LoggerFactory.getLogger(ApiControllersProduct.class);

    /**
     *
     * @return
     */
    @Override

    public List<Products> getProduct() {
            log.info("Retrieving all Products");
            return productRepo.findAll();
    }


    /**
     *
     * @param id
     * @return
     * @throws IdNotFoundException
     */
    @Override
    public Products getProductById(Integer id) throws IdNotFoundException {
        Products product;
        if(productRepo.findById(id).isEmpty()){
            log.error("Id not found exception");
            throw new IdNotFoundException();
        }
        else {
            log.info("Retrieving product for given id");
            product = productRepo.findById(id).get();
        }
        return product;
    }


    /**
     *
     * @param productDTO
     * @return
     */
    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Products product = convertDtoToEntity(productDTO);
        Products savedProduct = productRepo.save(product);
        ProductDTO savedProductDto = convertEntityToDto(savedProduct);
        log.info("Adding new product to table");
        return savedProductDto;
    }


    /**
     *
     * @param id
     * @param productDTO
     * @return
     * @throws IdNotFoundException
     */
    @Override
    public ProductDTO updateProduct(Integer id, ProductDTO productDTO) throws IdNotFoundException{

        if( productRepo.findById(id).isEmpty()){
            log.error("Cannot update product details... product doesnt exist with given id",id);
            throw new IdNotFoundException();
        }
        else{
            Products updatedProduct = productRepo.findById(id).get();
            updatedProduct.setProductName(productDTO.getProductName());
            updatedProduct.setProductDescription(productDTO.getProductDescription());
            updatedProduct.setPrice(productDTO.getPrice());
            updatedProduct.setCategoryId(productDTO.getCategoryId());
            updatedProduct.setUpdateDate(LocalDate.now()
            );
            productRepo.save(updatedProduct);
            log.info("Product Updated");
            ProductDTO updatedProductDto = convertEntityToDto(updatedProduct);
            return updatedProductDto;
        }
    }


    /**
     *
     * @param id
     * @return
     * @throws IdNotFoundException
     */
    @Override
    public String deleteProduct(Integer id) throws IdNotFoundException {
        if(productRepo.findById(id).isEmpty()){
            log.error("Id not found exception");
            throw new IdNotFoundException();
        }
        else {
            Products deleteProduct= productRepo.findById(id).get();
            productRepo.delete(deleteProduct);
            log.info("Product Deleted");
            return"Product Deleted";
        }

    }


    /**
     *
     * @return
     */
    @Override
    public List<Products> getProduct_A() {
        return productRepo.getProducts_A();
    }


    /**
     *
     * @param id
     * @param name
     * @return
     */
    @Override
    public Products updateName(Integer id,String name) {
        if(productRepo.findById(id).isEmpty()){
            log.error("Id not found exception");
            throw new IdNotFoundException();
        }
        else {
            log.info("Updating name of product with id:{}",id);
             productRepo.update(name,id);
             Products products = productRepo.findById(id).get();
             return products;
        }
    }


    /**
     *
     * @param id
     * @return
     */
    @Override
    public String softDelete(Integer id) {
        productRepo.soft_delete(id);
        return "Deleted";
    }


    /**
     *
     * @param products
     * @return
     */
    public ProductDTO convertEntityToDto(Products products){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(products.getProductName());
        productDTO.setProductDescription(products.getProductDescription());
        productDTO.setPrice(products.getPrice());
        productDTO.setCategoryId(products.getCategoryId());
        return productDTO;
    }


    /**
     *
     * @param productDTO
     * @return
     */
    public Products convertDtoToEntity(ProductDTO productDTO){
        Products products =new Products();
        products.setProductName(productDTO.getProductName());
        products.setProductDescription(productDTO.getProductDescription());
        products.setPrice(productDTO.getPrice());
        products.setCategoryId(productDTO.getCategoryId());
        return products;
    }
}
