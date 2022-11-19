package com.example.gamestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Config {

    @Bean
    public Scanner createScanner() {
        return new Scanner(System.in);
    }

}
