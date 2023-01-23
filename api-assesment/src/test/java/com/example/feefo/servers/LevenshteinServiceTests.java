package com.example.feefo.servers;

import com.example.feefo.service.LevenshteinService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class LevenshteinServiceTests {

	@Mock
	@Autowired
	private LevenshteinService levenshteinService;

	@Test
	void calculateDistanceShouldReturnSameValue() {
		HashMap<String, String> testValues = new HashMap<>();
		testValues.put("Feefo","Ofeef");
		testValues.put("sitting","kitten");
		testValues.put("mango","pineapple");

		testValues.forEach((firstStr, secondStr) -> {
			Double firstDistance = this.levenshteinService.calculateDistance(firstStr, secondStr);
			Double lastDistance = this.levenshteinService.calculateDistance(firstStr, secondStr);
			Assertions.assertThat(firstDistance).isEqualTo(lastDistance);
		});

	}
}
