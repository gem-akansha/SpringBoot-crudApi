package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createDate= new Date(System.currentTimeMillis());
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private  Date updateDate = new Date(System.currentTimeMillis());
    @Column
    private boolean isActive= true;
    @Column
    private boolean isDeleted= false;



    //Getters and Setters

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
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





  //constructor
    public Category(Integer categoryId, String categoryName, String categoryDescription, Date createDate, Date updateDate, boolean isActive, boolean isDeleted) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }


    //default constructor
    public Category() {
        super();
    }


    //tostring method
    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
