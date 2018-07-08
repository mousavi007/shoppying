package com.mousavi007.shop.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"product","factor"})
public class Factor_item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Produc_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "facto_id")
    private Factor factor;

    private int Quantity;

}
