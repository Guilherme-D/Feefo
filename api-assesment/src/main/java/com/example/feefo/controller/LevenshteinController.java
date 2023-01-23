package com.example.feefo.controller;

import com.example.feefo.models.RequestEntity.LevenshteinRequest;
import com.example.feefo.service.LevenshteinService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/")
public class LevenshteinController {

	@Autowired
	private LevenshteinService levenshteinService;

	@ApiOperation(value = "Calculate the Levenshtein distance",
			notes = "Calculate the Levenshtein distance, normalizing the value between 0 and 1",
			response = Double.class,
			responseContainer = "ResponseEntity")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "the calculated value of Levenshtein distance")
	})
	@PostMapping(value = "calculate-distance")
	public ResponseEntity<Double> calculateDistance(
			@ApiParam(value = "The request with 2 strings to be evaluated, the input (typed by user) and the actual title (registered in database)", required = true)
			@RequestBody @Valid LevenshteinRequest request
	){
		return ResponseEntity.ok(levenshteinService.calculateDistance(request.getInput(), request.getTitle()));
	}
}
