/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Windows 10
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.dna.service" ,
    "com.dna.repository"
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
     public Cloudinary cloudinary(){
         Cloudinary c = new Cloudinary(ObjectUtils.asMap(
             "cloud_name", "dzctkszri", 
             "api_key" , "389698265739658" , 
             "api_secret", "-03X4ykFEkbBdI9cNYi-DCS9qdw",
             "secure", true 
         ));
         return c;
     }
     
    


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService)
               .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/signin")
                .usernameParameter("username")
                .passwordParameter("password");
       http.formLogin().defaultSuccessUrl("/user/").failureUrl("/signin?error");
       http.logout().logoutSuccessUrl("/signin");
       http.exceptionHandling().accessDeniedPage("/signin?accessDenied");
       http.authorizeRequests().antMatchers("/").permitAll()
               .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
               .antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
//       http.exceptionHandling().accessDeniedPage("/user/?accessAuthorize");
       http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web); //To change body of generated methods, choose Tools | Templates.
    }
    
    
  
}

// http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
//               .and().authorizeRequests().antMatchers("/user/**").hasRole("USER")
//               .anyRequest().permitAll().and().formLogin().loginPage("/user/signin").loginProcessingUrl("/user/signin")
//               .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/profile")
//               .failureUrl("/user/signin?error=failed").and().exceptionHandling().accessDeniedPage("/user/signin?error=denied");
