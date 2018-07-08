package com.mousavi007.shop.Service;

import com.mousavi007.shop.Domain.Factor_item;
import com.mousavi007.shop.Repository.Factor_ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class FactorItemServiceImpl implements FactorItemService {


    final Factor_ItemRepository factor_itemRepository;

    public FactorItemServiceImpl(Factor_ItemRepository factor_itemRepository) {
        this.factor_itemRepository = factor_itemRepository;
    }

    @Override
    public Factor_item saveItem(Factor_item factor_item) {
        return factor_itemRepository.save(factor_item);
    }
}
