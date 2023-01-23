package com.example.feefo.controllers;

import com.example.feefo.controller.LevenshteinController;
import com.example.feefo.models.RequestEntity.LevenshteinRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LevenshteinControllerTests {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private LevenshteinController levenshteinController;

	@Test
	void contextLoads() {
		Assertions.assertThat(this.levenshteinController).isNotNull();
	}

	@Test
	public void calculateDistanceShouldReturnSameValue() {

		String url = "http://localhost:" + port + "/calculate-distance";
		LevenshteinRequest levenshteinRequestLive = new LevenshteinRequest("Live", "Liveing");
		LevenshteinRequest levenshteinRequestTest = new LevenshteinRequest("Test", "Testing");
		Assertions.assertThat(
				this.restTemplate.postForObject(url, levenshteinRequestLive, Double.class)
		).isEqualTo(this.restTemplate.postForObject(url, levenshteinRequestTest, Double.class));

	}

}
