package com.muscu.carnetMusculation;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = { "com.*" })
@EnableAutoConfiguration
public class CarnetMusculationApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CarnetMusculationApplication.class, args);
	}
}

