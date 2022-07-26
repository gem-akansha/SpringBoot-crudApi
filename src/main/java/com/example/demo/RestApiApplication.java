package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Crud Api " ,version = "2.7.1" , description = "Crud Api Using Spring Boot") )
public class RestApiApplication {

	public static void main(String[] args) {


		 SpringApplication.run(RestApiApplication.class, args);
	}

}
