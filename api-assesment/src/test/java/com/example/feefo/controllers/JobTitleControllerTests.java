package com.example.feefo.controllers;

import com.example.feefo.controller.JobTitleController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobTitleControllerTests {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private JobTitleController jobTitleController;

	@Test
	void contextLoads() {
		Assertions.assertThat(this.jobTitleController).isNotNull();
	}

	@Test
	public void normalizedValueShouldReturnSameValue() {
		HashMap<String, String> responseMapping = new HashMap<>();
		responseMapping.put("Java engineer", "Software engineer");
		responseMapping.put("C# engineer", "Software engineer");
		responseMapping.put("Accountant", "Accountant");
		responseMapping.put("Chief Accountant", "Accountant");
		responseMapping.put("Architect", "Architect");
		responseMapping.put("Senior Architect", "Architect");

		String url = "http://localhost:" + port + "/job-title/normalized-title?input=";

		responseMapping.forEach((input, answer) -> {
			String encodedInput = "";
			try {
				encodedInput = URLEncoder.encode(input, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				System.out.println("Unable to encode");
				e.printStackTrace();
			}
			Assertions.assertThat(
					this.restTemplate.getForObject(url + encodedInput, String.class)
			).isEqualTo(answer);
		});

	}

}
