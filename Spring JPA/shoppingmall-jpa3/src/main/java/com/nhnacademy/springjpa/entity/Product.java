package com.nhnacademy.springjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Integer productId;

    @Column(name="CategoryID")
    private Integer categoryId;

    @Column(name="ModelNumber")
    private String modelNumber;

    @Column(name="ModelName")
    private String ModelName;

    @Column(name="ProductImage")
    private String productImage;

    @Column(name="Description")
    private String description;

    @JoinColumn(name="CategoryID")
    @ManyToOne(fetch= FetchType.LAZY)
    private Category category;
}
