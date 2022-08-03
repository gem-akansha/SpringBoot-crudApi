package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
//import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor




@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column
    private String categoryName;
    @Column

    private String categoryDescription;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")

    //private Date createDate= new Date(System.currentTimeMillis());
    private LocalDate createDate= LocalDate.now();

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")

   // private  Date updateDate = new Date(System.currentTimeMillis());
    private LocalDate updateDate= LocalDate.now();
    @Column
    private boolean isActive= true;
    @Column
    private boolean isDeleted= false;



    //Getters and Setters

//    public Integer getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Integer categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
//
//    public String getCategoryDescription() {
//        return categoryDescription;
//    }
//
//    public void setCategoryDescription(String categoryDescription) {
//        this.categoryDescription = categoryDescription;
//    }
//
//    public Date getCreateDate() {
//        return createDate;
//    }
//
    @SuppressWarnings({"unchecked", "deprecated"})
    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
                //new Date(System.currentTimeMillis());
    }

//    public Date getUpdateDate() {
//        return updateDate;
//    }
//
//    public void setUpdateDate(Date updateDate) {
//        this.updateDate = updateDate;
//    }


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






  //constructor
    public Category(Integer id,String categoryName, String categoryDescription ) {
         this.categoryId = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public Category(String categoryName, String categoryDescription ) {

        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    //default constructor
//    public Category() {
//
//    }



}
