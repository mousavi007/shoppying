package com.mousavi007.shop.Service;

import com.mousavi007.shop.Commands.ProductsCommands;
import com.mousavi007.shop.Converter.ProductConv;
import com.mousavi007.shop.Domain.Company;
import com.mousavi007.shop.Domain.Product;
import com.mousavi007.shop.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product AddProduct(ProductsCommands productsCommands, Company company, Byte[] image) {
        Product product=new Product();
        ProductConv productConv=new ProductConv(product,productsCommands);
        product=productConv.commandToProduct();
        product.setCompany(company);
        product.setImage(image);
        return productRepository.save(product);
    }

    @Override
    public Page<Product> finaAllPagable(Pageable pageable) {

        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long Id) {

        return productRepository.findById(Id).get();
    }
}
