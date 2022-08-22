package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate= LocalDate.now();
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate= LocalDate.now();
    @Column
    private boolean isActive = true;
    @Column
    private boolean isDeleted = false;

    public Products(Integer productId,String productName,String productDescription,Integer price,Integer categoryId){
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.categoryId = categoryId;
    }
}
