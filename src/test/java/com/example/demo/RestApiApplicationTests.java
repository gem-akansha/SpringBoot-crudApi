package com.example.demo;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Models.Category;
import com.example.demo.Repo.CategoryRepo;
import com.example.demo.controller.ApiControllersCategory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RestApiApplicationTests {

	@Autowired
	ApiControllersCategory apiControllersCategory;

	@Autowired
	CategoryRepo categoryRepo;

	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest")
			.withUsername("postgres")
			.withPassword("root")
			.withDatabaseName("test");

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry){
		registry.add("spring.datasource.url",container::getJdbcUrl);
		registry.add("spring.datasource.username",container::getUsername);
		registry.add("spring.datasource.password",container::getPassword);
	}


	@Order(1)
	@Test
	void testGetCategory() {
		ResponseEntity Entity = apiControllersCategory.getCategory();

		System.out.println(Entity.getBody());
		List<Category> categoryList = (List<Category>) Entity.getBody();
		assertEquals(0,categoryList.size());
	}




	@Order(2)
	@Test
	void testSaveCategory() {
		CategoryDTO categoryDTO = new CategoryDTO("Laptop","Dell");
		ResponseEntity Entity = apiControllersCategory.saveCategory(categoryDTO);
		CategoryDTO categoryDTO1 = (CategoryDTO) Entity.getBody();
		assertEquals("Laptop",categoryDTO1.getCategoryName());
		//System.out.println(categoryDTO1.getCategoryName());
		//assertEquals(categoryDTO,categoryDTO1);

	}


	@Order(3)
	@Test
	void testGetCategoryById(){

	}


}
