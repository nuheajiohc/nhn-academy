package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select c from Category c")
    List<Category> findAllBy();

    @Query("select c from Category c where c.categoryId = ?1")
    List<Category> findBy(Integer categoryId);
}
