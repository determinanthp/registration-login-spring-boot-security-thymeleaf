package com.example.registrationloginspringbootsecuritythymeleaf.web;

import com.example.registrationloginspringbootsecuritythymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;
    private String registration;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return registration;
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute ("user") UserRegistrationDto registrationDto){
        userService.save(registrationDto);
        return "redirect:registration?success";
    }
}
