/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.configs;
import com.dna.formatter.CategoryPostFormatter;
import com.dna.formatter.CommentFormatter;
import com.dna.formatter.PostFormatter;
import com.dna.formatter.UserFormatter;
import com.dna.validator.ReportValidator;
import com.dna.validator.UserValidator;
import com.dna.validator.WebAppValidator;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author Windows 10
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.dna.controllers" ,
    "com.dna.repository" , 
    "com.dna.service" , 
    "com.dna.validator"
   
})
public class WebApplicationContextConfig implements WebMvcConfigurer {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/bootstrap/**")
                .addResourceLocations("/resources/bootstrap/");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/resources/css/");
       registry.addResourceHandler("/fonts/**")
               .addResourceLocations("/resources/fonts/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/resources/images/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/resources/js/");
        
    }
    @Override
    public Validator getValidator() {
        return validator();
    }
    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
    
    @Bean
    public WebAppValidator userValidator(){
        
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UserValidator());
        
        WebAppValidator v = new WebAppValidator();
        v.setSpringValidator(springValidators);
        
        return v;
        
    }
    @Bean
    public WebAppValidator reportValidator(){
          Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new ReportValidator());
        
        WebAppValidator v = new WebAppValidator();
        v.setSpringValidator(springValidators);
        
        return v;
    }
   
    
    @Override
   public  void addFormatters(FormatterRegistry registry) {
       registry.addFormatter(new CategoryPostFormatter());
       registry.addFormatter(new UserFormatter());
       registry.addFormatter(new PostFormatter());
       registry.addFormatter(new CommentFormatter());
    }
   
   
    
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasename("messages");
        return resource;
    }
     @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

}
