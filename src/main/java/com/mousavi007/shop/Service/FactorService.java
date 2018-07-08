package com.mousavi007.shop.Service;

import com.mousavi007.shop.Domain.Factor;
import com.mousavi007.shop.Domain.Factor_item;

public interface FactorService {
    Factor addFactorItems(Factor factor, Factor_item factor_item);
}
