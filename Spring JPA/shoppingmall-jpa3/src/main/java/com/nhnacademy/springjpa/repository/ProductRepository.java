package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Category;
import com.nhnacademy.springjpa.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p")
    List<Product> findAllBy();

    @Query("select p from Product p where p.productId = ?1")
    Product findByProductId(Integer productId);

    @Query("select p from Product p where p.modelNumber =?1")
    Product findByModelName(String modelName);
}
