package com.mousavi007.shop.Repository;

import com.mousavi007.shop.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {


}
