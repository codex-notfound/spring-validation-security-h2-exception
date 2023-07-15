package com.example.validationsecurityh2exception.config;

import java.net.http.HttpRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSpringSecurity {

    @Bean
    SecurityFilterChain defaulSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().ignoringRequestMatchers("/save")
            .authorizeHttpRequests()
            .requestMatcher("/contact").permitAll()           
            .requestMatcher("/contact/**").permitAll()
            .requestMatcher("/login").permitAll()
            .requestMatcher("/save").permitAll()
            .formLogin(login->login.loginPage("/login").defaultSuccessUrl("/contact").failureUrl("/login?error=true").permitAll())
            .logout(logout->logout.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll())
            .httpBasic();
        
        return http.build();
    }
    
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                            .username("user")
                            .password("12345")
                            .roles("user")
                            .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                            .username("amdin")
                            .password("12345")
                            .roles("user", "admin")
                            .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
