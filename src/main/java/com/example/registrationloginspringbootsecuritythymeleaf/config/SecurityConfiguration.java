package com.example.registrationloginspringbootsecuritythymeleaf.config;

import com.example.registrationloginspringbootsecuritythymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.hibernate.criterion.Restrictions.and;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
           DaoAuthenticationProvider auth= new DaoAuthenticationProvider();
           and();
           auth.setUserDetailsService(userService);
           auth.setPasswordEncoder(passwordEncoder());
           return auth;
    }
@Override
    protected void configure(HttpSecurity http) throws Exception{
    http
            .authorizeRequests()
            .antMatchers(
            "/registration**",
            "/js**",
            "/css**",
            "/img**", "/admin/**").hasRole("ADMIN")
            .antMatchers("/user/").hasAnyRole("USER", "ADMIN")
            anyRequest(authenticationManagerBean()).authenticated()
                    .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            http.logout()
                    .logoutUrl("logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();
    }

    private AuthenticatedAuthorizationManager<Object> anyRequest(AuthenticationManager authenticationManager) {

        return null;
    }
}
