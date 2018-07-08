package com.mousavi007.shop.Converter;

import com.mousavi007.shop.Commands.ProductsCommands;
import com.mousavi007.shop.Domain.Product;

import java.time.LocalDateTime;

public class ProductConv {

    final Product product;
    final ProductsCommands productsCommands;

    public ProductConv(Product product, ProductsCommands productsCommands) {

        this.product = product;
        this.productsCommands = productsCommands;
    }
    public Product commandToProduct(){

        product.setId(productsCommands.getId());
        product.setQuantity(productsCommands.getQuantity());
        product.setProduct_name(productsCommands.getProduct_name());
        product.setPrice(productsCommands.getPrice());
        product.setDateTime(LocalDateTime.now());
        return product;
    }

    public ProductsCommands productToCommand(){

        productsCommands.setId(product.getId());
        productsCommands.setPrice(product.getPrice());
        productsCommands.setProduct_name(product.getProduct_name());
        return productsCommands;

    }
}
