package com.nhnacademy.springjpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Orders")
public class Order {
    @EmbeddedId
    private Pk pk;

    @Column(name = "OrderDate")
    private LocalDateTime orderDate;

    @Column(name = "ShipDate")
    private LocalDateTime shipDate;

    @MapsId(value="userId")
    @ManyToOne
    @JoinColumn(name="UserID")
    private User user;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "OrderID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer orderId;
        @Column(name = "UserID")
        private String userId;
    }
}
