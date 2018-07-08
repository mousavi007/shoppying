package com.mousavi007.shop.Controller;


import com.mousavi007.shop.Domain.*;
import com.mousavi007.shop.Repository.FactorRepository;
import com.mousavi007.shop.Repository.Factor_ItemRepository;
import com.mousavi007.shop.Repository.ProductRepository;
import com.mousavi007.shop.Repository.UserRepository;
import com.mousavi007.shop.Service.ProductService;
import com.mousavi007.shop.Util.Pager;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/shop")
public class ShopController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    FactorRepository factorRepository;


    @Autowired
    Factor_ItemRepository factor_itemRepository;
    @Autowired
    ProductService productService;

    @Autowired
    UserRepository userRepository;

    @GetMapping({"","/"})
    public String Shop(Model model, @RequestParam("pageSize") Optional<Integer> pageSize,
                       @RequestParam("page") Optional<Integer> page){

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();

        if(auth.isAuthenticated()){
            String users=auth.getName();
            if(!users.contains("anonymousUser")) {
                User user = userRepository.findByEmail(users).get();
                if(user.getRoles().stream().map(x->x.getName()).collect(Collectors.toList()).contains("ROLE_ADMIN")){
                    return "redirect:/admin";
                }
                Sort sort=new Sort(Sort.Direction.ASC,"price");
                Page<Product> productPage = productService.finaAllPagable(PageRequest.of(evalPage, evalPageSize,sort));
                Pager pager = new Pager(productPage.getTotalPages(), productPage.getNumber(), BUTTONS_TO_SHOW);
                log.debug(String.valueOf(productPage.getTotalPages()));
                Optional<Factor> factorOptional = factorRepository.findFactorByPurchaseNullAndUserEquals(user);
                Factor factor=new Factor();
                if(factorOptional.isPresent()){

                    factor=factorOptional.get();
                }                log.debug("Authenticated");

                model.addAttribute("selectedPageSize", evalPageSize);
                model.addAttribute("pageSizes", PAGE_SIZES);
                model.addAttribute("pager", pager);

                model.addAttribute("productPage",productPage);

                model.addAttribute("factor", factor);
                model.addAttribute("sum", factor.getFactor_items().stream().map(item -> item.getProduct().getPrice()).collect(Collectors.toList()));
            }
            return "shop/index";
        }
        else {

            log.debug("Not Authenticated");
            return "shop/index";
        }
    }

    @GetMapping("/addtofactor")
    RedirectView AddToFactor(RedirectAttributes attributes, @RequestParam("factorId")Optional<Long> factorId, @RequestParam("productId") Long productId){

        Factor factor=new Factor();
        Product product=productService.findById(productId);
        if(factorId.isPresent()){
            factor=factorRepository.findById(factorId.get()).get();
        }
        else {
            Authentication auth= SecurityContextHolder.getContext().getAuthentication();
            String users=auth.getName();
            User user = userRepository.findByEmail(users).get();
            factor.setDateTime(LocalDateTime.now());
            factor.setUser(user);
            factor=factorRepository.save(factor);
        }
        if(!(factor.getFactor_items().stream().map(x -> x.getProduct()).collect(Collectors.toList()).contains(product))) {
            Factor_item factor_item = new Factor_item();
            factor_item.setFactor(factor);
            factor_item.setProduct(product);
            factor_item.setQuantity(1);
            factor_itemRepository.save(factor_item);
            factor.AddFactorItem(factor_item);
            factorRepository.save(factor);
        }





        return new RedirectView("/card");
    }

    @RequestMapping("/{id}/image")
    public void renderImageFromDb(@PathVariable String id, HttpServletResponse response)throws IOException {

        Product product= productService.findById(Long.valueOf(id));
        if (product.getImage() != null) {
            byte[] byteArray = new byte[product.getImage().length];
            int i = 0;
            for (Byte wrappedByte : product.getImage()) {
                byteArray[i++] = wrappedByte;
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());

        }
    }

}
