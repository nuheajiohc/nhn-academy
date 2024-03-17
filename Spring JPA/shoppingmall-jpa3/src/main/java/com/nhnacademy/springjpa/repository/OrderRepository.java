package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Category;
import com.nhnacademy.springjpa.entity.Order;
import com.nhnacademy.springjpa.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Product, Integer> {

    @Query("select o from Order o")
    List<Product> findAllBy();

    @Query("select o from Order o where o.pk.orderId = ?1")
    List<Order> findAllByUserId(int userId);
}
