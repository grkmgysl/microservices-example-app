package com.grkmgysl.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grkmgysl.orderservice.dto.OrderLineItemsDto;
import com.grkmgysl.orderservice.dto.OrderRequest;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class OrderServiceApplicationTests {

	@Container
	public static MySQLContainer<?> mySqlDB = new MySQLContainer<>
			("mysql:5.7.37")
			.withDatabaseName("order-service")
			.withUsername("root")
			.withPassword("mysql");


	@DynamicPropertySource
	public static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url",mySqlDB::getJdbcUrl);
		registry.add("spring.datasource.username", mySqlDB::getUsername);
		registry.add("spring.datasource.password", mySqlDB::getPassword);

	}

	@Autowired
	private MockMvc mockMvc;

	//converts pojo to json
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldCreateOrder() throws Exception {

		OrderRequest orderRequest = getOrderRequest();

		String requestString = objectMapper.writeValueAsString(orderRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestString))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	private OrderRequest getOrderRequest() {
		List<OrderLineItemsDto> orderLineItemsDtoList = new ArrayList<>();
		orderLineItemsDtoList.add(new OrderLineItemsDto("iphone", BigDecimal.valueOf(1200), 1));

		return new OrderRequest(orderLineItemsDtoList);
	}

}
