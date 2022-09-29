package com.grkmgysl.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grkmgysl.productservice.dto.ProductRequest;
import com.grkmgysl.productservice.dto.ProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private MockMvc mockMvc;

	//converts pojo to json
	@Autowired
	private ObjectMapper objectMapper;

	//when app starts mongo 4.4.2 downloads and spring.data.mongodb.uri is set dynamically
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();

		String requestString = objectMapper.writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
						.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
						.content(requestString))
				.andExpect(status().isCreated());
	}

	@Test
	void shouldReturnProductList() throws Exception {


		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$..id").isNotEmpty());
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("book1")
				.description("book about something")
				.price(BigDecimal.valueOf(1200))
				.build();
	}

}
