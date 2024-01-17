package com.example.registrationloginspringbootsecuritythymeleaf.service;

import com.example.registrationloginspringbootsecuritythymeleaf.model.User;
import com.example.registrationloginspringbootsecuritythymeleaf.web.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
