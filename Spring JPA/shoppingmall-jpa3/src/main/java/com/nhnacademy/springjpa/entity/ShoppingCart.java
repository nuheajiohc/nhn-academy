package com.nhnacademy.springjpa.entity;

import com.nhnacademy.springjpa.entity.OrderDetails.Pk;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "ShoppingCart")
public class ShoppingCart {

    @EmbeddedId
    private Pk pk;

    @Column(name = "CartID")
    private String cartId;

    @Column(name = "Quantity")
    private Integer ProductId;


    @Column(name = "DateCreated")
    private LocalDateTime dateCreated = LocalDateTime.now();

    @MapsId(value="productId")
    @ManyToOne
    @JoinColumn(name="ProductID")
    private Product product;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable{
        @Column(name="RecordID")
        private Integer recordId;

        @Column(name="ProductID")
        private Integer productId;
    }
}
