package com.mousavi007.shop.Controller;

import com.mousavi007.shop.Commands.CompanyCommands;
import com.mousavi007.shop.Commands.ProductsCommands;
import com.mousavi007.shop.Domain.Company;
import com.mousavi007.shop.Domain.Product;
import com.mousavi007.shop.Domain.Role;
import com.mousavi007.shop.Repository.RoleRepository;
import com.mousavi007.shop.Service.CompanyService;
import com.mousavi007.shop.Service.ProductService;
import com.mousavi007.shop.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CompanyService companyService;
    @Autowired
    ProductService productService;

    @GetMapping({"","/"})
    public String AdminPanel(Model model){
        Role role=new Role();
        role=roleRepository.getOne(2);
        model.addAttribute("userCount",userService.countUserByRole(role));

        return "admin/index";
    }

    @GetMapping("/product")
    public String ProductShow(Model model){
        String message= (String) model.asMap().get("Message");
        model.addAttribute("message",message);
        return "admin/product/showproduct";
    }

    @GetMapping("/addproduct")
    public String AddProduct(Model model){

        model.addAttribute("company",companyService.CompanyList());
        model.addAttribute("product", new ProductsCommands());
        return "admin/product/addproduct";
    }

    @PostMapping("/addproduct")
    public RedirectView SaveProduct(@ModelAttribute("product") ProductsCommands productsCommands, @RequestParam("image") Optional<MultipartFile> file, RedirectAttributes attributes) throws IOException {

        Byte[] image= getImage(file.get());
        Product product= productService.AddProduct(productsCommands,companyService.getCompany(productsCommands.getCompany()), image);
        if(product.getId()>0) {
            attributes.addFlashAttribute("Message",  "محصول " + product.getProduct_name() + " با موفقیت ثبت شد.");

            return new RedirectView("/admin/product");
            /*should complete code*/
        }
        else {
            attributes.addFlashAttribute("Message",  "محصول " + product.getProduct_name() + " ثبت نشد لطفا دوباره آن را ثبت نمایید.");
            return new RedirectView("/admin/product");
            /*should complete code*/
        }
    }

    @GetMapping("/showproduct")
    public String ShowProduct(Model model){

        model.addAttribute("Message", "");

        return "admin/product/showproduct";
    }

    @GetMapping("/company")
    public String ShowCompany(Model model){

        String message= (String) model.asMap().get("Message");
        model.addAttribute("message",message);
        return "admin/company/showcompany";
    }

    @GetMapping("/addcompany")
    public String AddCompany(Model model){

        String message= (String) model.asMap().get("Message");
        model.addAttribute("company", new CompanyCommands());
        model.addAttribute("message",message);
        return "admin/company/addcompany";
    }

    @PostMapping("/addcompany")
    public RedirectView SaveCompany(@ModelAttribute("company") CompanyCommands companyCommands, @RequestParam("image") Optional<MultipartFile> file, RedirectAttributes attributes) throws IOException {

        Byte[] image= getImage(file.get());
        Company company= companyService.addCompany(companyCommands, image);
        if(company.getId()>0) {
            attributes.addFlashAttribute("Message",  "شرکت " + company.getCompanyName() + " با موفقیت ثبت شد.");

            return new RedirectView("/admin/company");
            /*should complete code*/
        }
        else {
            attributes.addFlashAttribute("Message",  "شرکت " + company.getCompanyName() + " ثبت نشد لطفا دوباره آن را ثبت نمایید.");
            return new RedirectView("/admin/company");
            /*should complete code*/
        }
    }


































    public Byte[] getImage(MultipartFile file) throws IOException {

        Byte[] byteObjects = new Byte[file.getBytes().length];

        try{

        int i = 0;

        for (byte b : file.getBytes()){
            byteObjects[i++] = b;
        }


        } catch (IOException e) {
        //todo handle better
        log.error("Error occurred", e);

        e.printStackTrace();
    }
        log.debug("load image success");
        return byteObjects;

}
}
