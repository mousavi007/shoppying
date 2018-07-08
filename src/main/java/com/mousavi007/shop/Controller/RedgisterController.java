package com.mousavi007.shop.Controller;

import com.mousavi007.shop.Domain.Role;
import com.mousavi007.shop.Domain.User;
import com.mousavi007.shop.Service.RoleService;
import com.mousavi007.shop.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/register")
public class RedgisterController {

    final UserService userService;

    final RoleService roleService;
    public RedgisterController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping({"","/"})
    public String getRegister(Model model){

        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping({"","/"})
    public String postRegister(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        if(userService.existUserByEmail(user.getEmail())){
            bindingResult
                    .rejectValue("email", "error.email","* این ایمیل قبلا ثبت شده است. ");
        }
        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "register";
        }
        Role role=roleService.getRole(2);
        user.getRoles().add(role);
        userService.addUser(user);

        return "redirect:/index";
    }

}
