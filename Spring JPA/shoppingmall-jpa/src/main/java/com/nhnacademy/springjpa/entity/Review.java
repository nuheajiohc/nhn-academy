package com.nhnacademy.springjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Reviews")
public class Review {

    @Id
    @Column(name = "ReviewID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @Column(name="ProductID")
    private Integer productId;

    @Column(name="UserID")
    private String userId;

    @Column(name="Rating")
    private Integer rating;

    @Column(name="Comments")
    private String comments;
}
