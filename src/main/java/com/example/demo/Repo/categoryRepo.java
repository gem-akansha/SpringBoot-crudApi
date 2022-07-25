package com.example.demo.Repo;

import com.example.demo.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface categoryRepo extends JpaRepository<Category,Integer> {
}
