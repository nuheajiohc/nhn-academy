package com.nhnacademy.springjpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Reviews")
public class Review {

    @EmbeddedId
    private Pk pk;

    @Column(name="Rating")
    private Integer rating;

    @Column(name="Comments")
    private String comments;


    @MapsId(value="productId")
    @ManyToOne
    @JoinColumn(name="ProductID")
    private Product product;

    @MapsId(value="userId")
    @ManyToOne
    @JoinColumn(name="UserID")
    private User user;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable{
        @Column(name="ReviewID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer reviewId;
        @Column(name="ProductID")
        private Integer productId;
        @Column(name="UserID")
        private String userId;
    }
}
