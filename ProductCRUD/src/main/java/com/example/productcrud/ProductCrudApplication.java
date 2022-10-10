package com.example.productcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProductCrudApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ProductCrudApplication.class, args);
    }

}
