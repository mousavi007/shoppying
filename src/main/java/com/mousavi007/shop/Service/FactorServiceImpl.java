package com.mousavi007.shop.Service;

import com.mousavi007.shop.Domain.Factor;
import com.mousavi007.shop.Domain.Factor_item;
import com.mousavi007.shop.Repository.FactorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class FactorServiceImpl implements FactorService {

    final FactorRepository factorRepository;

    public FactorServiceImpl(FactorRepository factorRepository) {
        this.factorRepository = factorRepository;
    }

    @Override
    public Factor addFactorItems(Factor factor, Factor_item factor_item) {

        factor_item.setFactor(factor);
        factor.AddFactorItem(factor_item);

        return factorRepository.save(factor);
    }
}
