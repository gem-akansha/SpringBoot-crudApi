package com.example.demo.Services;

import com.example.demo.Exception.IdNotFoundException;
import com.example.demo.Models.Products;
import com.example.demo.Repo.productRepo;
import com.example.demo.controller.apiControllersProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
public class ProductService implements ProductInter {

    @Autowired
    public productRepo ProductRepo;

    Logger log = LoggerFactory.getLogger(apiControllersProduct.class);

    @Override

    public List<Products> getProduct() {
            log.info("Retrieving all Products");
            return ProductRepo.findAll();
    }

    @Override
    public Products getOneProduct(Integer id) throws IdNotFoundException {
        Products product;
        if(ProductRepo.findById(id).isEmpty()){
            log.error("Id not found exception");
            throw new IdNotFoundException();
        }
        else {
            log.info("Retrieving product for given id");
            product = ProductRepo.findById(id).get();
        }
        return product;
    }

    @Override
    public String saveProduct(Products product) {

        ProductRepo.save(product);
        log.info("Adding new product to table");
        return "Saved.....";
    }

    @Override
    public Products updateProduct(Integer id, Products product) throws IdNotFoundException{

        if( ProductRepo.findById(id).isEmpty()){
            log.error("Cannot update product details... product doesnt exist with given id",id);
            throw new IdNotFoundException();
        }
        else{
            Products updatedProduct = ProductRepo.findById(id).get();
           // updatedProduct.setCategoryId(product.getCategoryId());
            updatedProduct.setProductName(product.getProductName());
            updatedProduct.setProductDescription(product.getProductDescription());
            updatedProduct.setPrice(product.getPrice());
            //updatedProduct.setCreateDate(product.getCreateDate());

            updatedProduct.setUpdateDate(LocalDate.now()
                  //  new Date(System.currentTimeMillis())
            );
            //updatedProduct.setUpdateDate(product.getUpdateDate());
            //updatedProduct.setActive(product.getActive());
            //updatedProduct.setDeleted(product.getDeleted());
            ProductRepo.save(updatedProduct);
            log.info("Product Updated");
            return updatedProduct;
        }





//        public Product updateProduct(Product product, int productId) throws ResourceNotFoundException {
//            Product update;
//            if (productDao.findById(productId).isPresent()) {
//                update = productDao.findById(productId).get();
//                update.setProductName(product.getProductName());
//                update.setProductDescription(product.getProductDescription());
//                update.setPrice(product.getPrice());
//                // update.setCreateDate(product.getCreateDate());updateDate=new Date(System.currentTimeMillis());
//                Date date = new Date(System.currentTimeMillis());
//                update.setUpdateDate(date);
//                update.setActive(product.isActive());
//                update.setDeleted(product.isDeleted());
//                productDao.save(update);
//            } else {
//                throw new ResourceNotFoundException();
//            }
//            return update;
//        }
    }


    @Override
    public String deleteProduct(Integer id) throws IdNotFoundException {
        if(ProductRepo.findById(id).isEmpty()){
            log.error("Id not found exception");
            throw new IdNotFoundException();
        }
        else {
            Products deleteProduct= ProductRepo.findById(id).get();
            ProductRepo.delete(deleteProduct);
            log.info("Product Deleted");

            //ProductRepo.deleteById(id);
            return"Product Deleted";
        }

    }

    @Override
    public List<Products> getProduct_A() {
        return ProductRepo.getProducts_A();
    }

    @Override
    public Products updateName(Integer id,String name) {
        if(ProductRepo.findById(id).isEmpty()){
            log.error("Id not found exception");
            throw new IdNotFoundException();
        }
        else {

            log.info("tewst log");
             ProductRepo.update(name,id);
//            ProductRepo.demo(id);
             log.info("Here");
             Products products = ProductRepo.findById(id).get();
            System.out.println(products.getProductName());
             return products;
        }
    }

    @Override
    public String softDelete(Integer id) {
        ProductRepo.soft_delete(id);
        return "Deleted";
    }

//    @Override
//    public String updateSelectedProduct(Integer id, Products product) throws IdNotFoundException{
//        if(ProductRepo.findById(id).isEmpty()){
//            throw new IdNotFoundException();
//        }
//        else {
//            Products UpdateSelectedProduct = ProductRepo.findById(id).get();
//            UpdateSelectedProduct.setProductName(product.getProductName());
//            UpdateSelectedProduct.setProductDescription(product.getProductDescription());
//            UpdateSelectedProduct.setPrice(product.getPrice());
//            //updatedProduct.setCreateDate(product.getCreateDate());
//
//            UpdateSelectedProduct.setUpdateDate(new Date(System.currentTimeMillis()));
//            //updatedProduct.setUpdateDate(product.getUpdateDate());
//            UpdateSelectedProduct.setActive(product.getActive());
//            UpdateSelectedProduct.setDeleted(product.getDeleted());
//            ProductRepo.save(UpdateSelectedProduct);
//            return "Updated Successfully";
//        }
//
//    }
}
