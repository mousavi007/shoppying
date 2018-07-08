package com.mousavi007.shop.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Data
@EqualsAndHashCode(exclude = {"company"})
public class Product {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int Quantity;
    private String Product_name;
    private Long price;
    @Basic
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "product", cascade = ALL, orphanRemoval = true)
    private Set<Factor_item> factors_item=new HashSet<>();

    @Lob
    private Byte[] image;

    @ManyToOne
    private Company company;

    public void AddFactorItem(Factor_item factor_item)
    {
        this.factors_item.add(factor_item);
/*
        factor_item.setProduct(this);
*/
    }

    public void RemoveFactorItem(Factor_item factor_item)
    {
        this.factors_item.remove(factor_item);
/*
        factor_item.setProduct(null);
*/
    }

}
