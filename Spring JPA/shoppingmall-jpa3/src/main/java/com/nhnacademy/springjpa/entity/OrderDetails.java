package com.nhnacademy.springjpa.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name="OrderDetails")
public class OrderDetails {

    @EmbeddedId
    private Pk pk;

    @Column(name="Quantity")
    private Integer quantity;

    @Column(name="UnitCost")
    private BigDecimal unitCost;

    @MapsId(value="productId")
    @ManyToOne
    @JoinColumn(name="ProductID")
    private Product product;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable{
        @Column(name="OrderID")
        private Integer orderId;

        @Column(name="ProductID")
        private Integer productId;
    }
}
