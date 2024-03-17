package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Category;
import com.nhnacademy.springjpa.entity.ShoppingCart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCart.Pk> {

    @Query("select sc from ShoppingCart sc")
    List<ShoppingCart> findAllBy();

    @Query("select sc from ShoppingCart sc where sc.pk.recordId=?1")
    List<ShoppingCart> findByRecordId(String userId);
}
