package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Category;
import com.nhnacademy.springjpa.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllBy();
}
