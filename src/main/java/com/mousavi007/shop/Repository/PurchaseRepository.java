package com.mousavi007.shop.Repository;

import com.mousavi007.shop.Domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
}
