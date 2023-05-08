package com.example.nationallibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class NationalLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(NationalLibraryApplication.class, args);
    }

}
