package com.example.demo.Repo;

import com.example.demo.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository

public interface productRepo extends JpaRepository<Products,Integer> {
    @Query(value="select * from Products where product_name like 'A%' ",nativeQuery = true)
    public List<Products> getProducts_A();

    @Modifying
    @Transactional
    @Query(value = "update products set product_name = :name where product_id = :id ",nativeQuery = true)
    public void update(@Param(value = "name") String name, @Param(value = "id") Integer id);

    @Query(value = "select product_name from Products where product_id = :id",nativeQuery = true)
    public String demo(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query(value = "update Products set is_deleted = true where product_id = :id ",nativeQuery = true)
    public void  soft_delete(@Param("id") Integer id);
}
