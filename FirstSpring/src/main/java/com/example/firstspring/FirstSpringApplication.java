package com.example.firstspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirstSpringApplication {

    public static void main(String[] args) {
        //SpringApplication.run(FirstSpringApplication.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    }

}
