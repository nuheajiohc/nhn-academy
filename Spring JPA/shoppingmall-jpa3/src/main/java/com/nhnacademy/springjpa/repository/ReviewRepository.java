package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Category;
import com.nhnacademy.springjpa.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review,Review.Pk> {

    @Query("select r from Review r")
    List<Review> findAllBy();

    @Query("select r from Review r where r.pk.reviewId = ?1")
    List<Review> findByReviewId(Integer reviewId);
}
