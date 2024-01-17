package com.example.registrationloginspringbootsecuritythymeleaf.service;

import com.example.registrationloginspringbootsecuritythymeleaf.model.Role;
import com.example.registrationloginspringbootsecuritythymeleaf.model.User;
import com.example.registrationloginspringbootsecuritythymeleaf.repository.UserRepository;
import com.example.registrationloginspringbootsecuritythymeleaf.web.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        UserRegistrationDto registrationDto = null;
        User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail(), registrationDto.getPassword(), Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
