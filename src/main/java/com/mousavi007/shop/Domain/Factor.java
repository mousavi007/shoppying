package com.mousavi007.shop.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
@EqualsAndHashCode(exclude = {"user"})
public class Factor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private User user;

    @Basic
    private LocalDateTime dateTime;

    private Long price;

    @OneToMany(mappedBy = "factor", cascade = ALL, orphanRemoval = true)
    private Set<Factor_item> factor_items=new HashSet<>();

    @OneToOne
    private Purchase purchase;

    public void AddFactorItem(Factor_item factor_item)
    {
        this.factor_items.add(factor_item);
/*
        factor_item.setFactor(this);
*/
    }

    public void RemoveFactorItem(Factor_item factor_item)
    {
        this.factor_items.remove(factor_item);
/*
        factor_item.setFactor(null);
*/
    }

}

