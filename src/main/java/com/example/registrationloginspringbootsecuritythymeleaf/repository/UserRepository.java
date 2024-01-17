package com.example.registrationloginspringbootsecuritythymeleaf.repository;

import com.example.registrationloginspringbootsecuritythymeleaf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
