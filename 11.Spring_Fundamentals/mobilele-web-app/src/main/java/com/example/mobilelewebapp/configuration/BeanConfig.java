package com.example.mobilelewebapp.configuration;

import com.example.mobilelewebapp.models.dtos.ModelViewDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Configuration
public class BeanConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }

//    @Bean
//    public Map<String, Set<ModelViewDto>> modelsByBrandName() {
//        return new LinkedHashMap<>();
//    }
}
