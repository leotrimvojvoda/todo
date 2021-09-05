package com.example.todo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/register","/save")
                .permitAll()
                .anyRequest()
                .authenticated()//Any Authentication
                .and()
                .formLogin()//USE FORM LOGIN -- DEFAULT
                .loginPage("/login")// THE LOGIN PAGE CAN BE FOUND IN THE MAIN CRONTROLER /login.
                .permitAll()
                .and();
               // .rememberMe()//Allow remember me option
               // .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21)); // Remember for 21 days

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }


}










