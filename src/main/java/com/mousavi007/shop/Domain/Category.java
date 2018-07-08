package com.mousavi007.shop.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String Name;

    @OneToMany(mappedBy = "ParentCategory")
    private Set<Category>  SubCategory;

    @ManyToOne
    private Category ParentCategory;
}
