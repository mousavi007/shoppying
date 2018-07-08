package com.mousavi007.shop.Repository;

import com.mousavi007.shop.Domain.Factor;
import com.mousavi007.shop.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FactorRepository extends JpaRepository<Factor,Long>{
    public Optional<Factor> findFactorByPurchaseNullAndUserEquals(User user);

}
