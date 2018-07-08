package com.mousavi007.shop.Service;

import com.mousavi007.shop.Commands.ProductsCommands;
import com.mousavi007.shop.Domain.Company;
import com.mousavi007.shop.Domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product AddProduct(ProductsCommands productsCommands, Company company, Byte[] image);

    Page<Product> finaAllPagable(Pageable pageable);
    Product findById(Long Id);
}
