package com.example.transactiostatistics.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {
//modelmapper to map the the transactionRequest object to Transaction class
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
