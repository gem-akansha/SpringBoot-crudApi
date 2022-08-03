package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

//import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column
    private String productName;
    @Column
    private String productDescription;
    @Column
    private Integer price;
    @Column
    private Integer categoryId;

    @Column
    //@Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
   // @CreatedDate
   // private Date createDate;
    //private Date createDate = new Date(System.currentTimeMillis());
    private LocalDate createDate= LocalDate.now();
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
  //  @CreatedDate
   // private  Date updateDate = new Date(System.currentTimeMillis());
    private LocalDate updateDate= LocalDate.now();
    @Column
    private boolean isActive = true;
    @Column
    private boolean isDeleted = false;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    public Products(){

    }

    public Products(Integer productId,String productName,String productDescription,Integer price,Integer categoryId){
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.categoryId = categoryId;
    }
}
