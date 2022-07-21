package com.example.demo.Repo;

import com.example.demo.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepo extends JpaRepository<Products,Integer> {


}
