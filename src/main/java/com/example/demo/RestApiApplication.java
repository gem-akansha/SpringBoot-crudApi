package com.example.demo;

import com.example.demo.Models.Category;
import com.example.demo.Models.Products;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Crud Api " ,version = "2.7.1" , description = "Crud Api Using Spring Boot") )
public class RestApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {

		 SpringApplication.run(RestApiApplication.class, args);
	}

}
