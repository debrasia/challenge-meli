package com.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.api.controller.CouponController;
import com.api.demo.ChallengeMeliApplication;
import com.api.models.CouponProductsRequest;
import com.api.models.CouponProductsResponse;

@SpringBootTest(classes=ChallengeMeliApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)

class ChallengeMeliApplicationTests {

	@Autowired
	private CouponController controller;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@Test
	void listWithProducts() {
		assertThat(controller).isNotNull();
		
		CouponProductsRequest request = new CouponProductsRequest();
		
		request.setAmount(42000);
		request.setProducts(Arrays.asList("MLA1318742325","MLA781333870","MLA1733065530"));
		
		ResponseEntity<CouponProductsResponse> response = this.restTemplate.postForEntity("http://localhost:" + port + "/coupon/", request, CouponProductsResponse.class);
		
		assertEquals(41319, response.getBody().getTotalAmount());
		assertEquals(Arrays.asList("MLA1318742325","MLA1733065530"), response.getBody().getProducts());
	}
	
	@Test
	void noProducts() {
		assertThat(controller).isNotNull();
		
		CouponProductsRequest request = new CouponProductsRequest();
		
		request.setAmount(1000);
		request.setProducts(Arrays.asList("MLA1318742325","MLA781333870","MLA1733065530"));
		
		ResponseEntity<CouponProductsResponse> response = this.restTemplate.postForEntity("http://localhost:" + port + "/coupon/", request, CouponProductsResponse.class);
		
		assertEquals(0, response.getBody().getTotalAmount());
		assertTrue(response.getBody().getProducts().isEmpty());		
	}

}
