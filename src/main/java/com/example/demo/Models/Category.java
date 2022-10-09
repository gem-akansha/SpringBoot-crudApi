package com.example.demo.Models;

import com.example.demo.DTO.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Category implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column
    private String categoryName;
    @Column
    private String categoryDescription;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate= LocalDate.now();

    @Column
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate= LocalDate.now();

    @Column
    private boolean isActive = true;
    @Column
     private boolean isDeleted = false;

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


}
