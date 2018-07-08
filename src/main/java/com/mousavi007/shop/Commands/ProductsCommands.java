package com.mousavi007.shop.Commands;

import com.mousavi007.shop.Domain.Company;
import com.mousavi007.shop.Domain.Factor_item;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Data
public class ProductsCommands {

    private Long Id;
    private int Quantity;
    private String Product_name;
    private Long price;
    private String dateTime;
    private String company;

}
