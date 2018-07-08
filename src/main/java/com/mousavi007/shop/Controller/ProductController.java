package com.mousavi007.shop.Controller;

import com.mousavi007.shop.Commands.ProductsCommands;
import com.mousavi007.shop.Converter.ProductConv;
import com.mousavi007.shop.Domain.Factor;
import com.mousavi007.shop.Domain.Product;
import com.mousavi007.shop.Domain.User;
import com.mousavi007.shop.Repository.FactorRepository;
import com.mousavi007.shop.Repository.ProductRepository;
import com.mousavi007.shop.Repository.UserRepository;
import com.mousavi007.shop.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    FactorRepository factorRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductService productService;

    @GetMapping("/detail/{id}")
    String productDetail(Model model,@PathVariable long id){

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String users=auth.getName();
        User user = userRepository.findByEmail(users).get();
        Optional<Factor> factorOptional = factorRepository.findFactorByPurchaseNullAndUserEquals(user);
        Factor factor=new Factor();
        if(factorOptional.isPresent()){

            factor=factorOptional.get();
        }


        model.addAttribute("factor", factor);
        model.addAttribute("sum", factor.getFactor_items().stream().map(item -> item.getProduct().getPrice()).collect(Collectors.toList()));
        Product product=productService.findById(id);
        ProductsCommands productsCommands=new ProductsCommands();
        ProductConv productConv=new ProductConv(product,productsCommands);
        productsCommands=productConv.productToCommand();
        model.addAttribute("factor", factor);
        model.addAttribute("sum", factor.getFactor_items().stream().map(item -> item.getProduct().getPrice()).collect(Collectors.toList()));
        model.addAttribute("product",productsCommands);



        return "shop/product/details";
    }
}
