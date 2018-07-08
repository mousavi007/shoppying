package com.mousavi007.shop.Controller;

import com.mousavi007.shop.Domain.Factor;
import com.mousavi007.shop.Domain.User;
import com.mousavi007.shop.Repository.FactorRepository;
import com.mousavi007.shop.Repository.Factor_ItemRepository;
import com.mousavi007.shop.Repository.ProductRepository;
import com.mousavi007.shop.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    Factor_ItemRepository factor_itemRepository;

    @Autowired
    FactorRepository factorRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping({"/home","/index", "/", ""})
    public String index(Model model){
       /* product product=new product();
        product.setProduct_name("miz");
        product.setPrice(5000l);
        productRepository.save(product);
        Factor factor=new Factor();
        factor.setDateTime(LocalDateTime.now());
        factorRepository.save(factor);


        Factor_item factor_item=new Factor_item();
        factor_item.setFactor(factor);
        factor_item.setProduct(product);
        factor_itemRepository.save(factor_item);
        factor.AddFactorItem(factor_item);
        product.AddFactorItem(factor_item);
        productRepository.save(product);

        log.debug(Boolean.toString(factor.getFactor_items().isEmpty()));
        log.debug(Boolean.toString(product.getFactors_item().isEmpty()));

        product product2=new product();
        product2=productRepository.findById(1l).get();
        log.debug(Boolean.toString(product2.getFactors_item().isEmpty()));
        product.RemoveFactorItem(factor_item);
        factor.RemoveFactorItem(factor_item);
        productRepository.save(product);




        Factor product1=new Factor();
        product1=factorRepository.findById(1l).get();
        log.debug(Boolean.toString(product1.getFactor_items().isEmpty()));

*/
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();

        if(auth.isAuthenticated()){
            String users=auth.getName();
            if(!users.contains("anonymousUser")) {
                User user = userRepository.findByEmail(users).get();
                if(user.getRoles().stream().map(x->x.getName()).collect(Collectors.toList()).contains("ROLE_ADMIN")){
                    return "redirect:/admin";
                }
                Optional<Factor> factorOptional = factorRepository.findFactorByPurchaseNullAndUserEquals(user);
                Factor factor=new Factor();
                if(factorOptional.isPresent()){

                    factor=factorOptional.get();
                }
                log.debug("Authenticated");


                model.addAttribute("factor", factor);
                model.addAttribute("sum", factor.getFactor_items().stream().map(item -> item.getProduct().getPrice()).collect(Collectors.toList()));
            }
            return "index";
        }
        else {

            log.debug("Not Authenticated");
            return "index";
        }
    }
}
