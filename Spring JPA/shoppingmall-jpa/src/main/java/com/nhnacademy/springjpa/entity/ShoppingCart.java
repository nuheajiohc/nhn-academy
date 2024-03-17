package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
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
@Table(name = "ShoppingCart")
public class ShoppingCart {

    @Id
    @Column(name = "RecordID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    @Column(name = "CartID")
    private String cartId;

    @Column(name = "Quantity")
    private Integer ProductId;

    @Column(name = "ProductID")
    private Integer productId;

    @Column(name = "DateCreated")
    private LocalDateTime dateCreated = LocalDateTime.now();
}
