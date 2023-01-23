package com.example.feefo.controller;

import com.example.feefo.models.JobTitles;
import com.example.feefo.models.RequestEntity.JobTitleRequest;
import com.example.feefo.service.JobTitlesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin
@RequestMapping("/job-title")
public class JobTitleController {

	@Autowired
	private JobTitlesService jobTitlesService;

	@ApiOperation(value = "Get the normalized title",
			notes = "Get the normalized title calculated using the Levenshtein distance",
			response = String.class,
			responseContainer = "ResponseEntity")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid input value"),
			@ApiResponse(code = 404, message = "There is no job title saved"),
			@ApiResponse(code = 200, message = "Closest title found")
	})
	@GetMapping(value = "normalized-title")
	public ResponseEntity<String> getNormalized(
			@ApiParam("The inputted value to be normalized")
			@RequestParam(required = false) String input
	) throws NotFoundException {
		return ResponseEntity.ok(this.jobTitlesService.getNormalizedTitle(input));
	}

	@ApiOperation(value = "Create new job title",
			notes = "Save new job title to database to use as normalized",
			response = JobTitles.class,
			responseContainer = "ResponseEntity")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Job title already exists"),
			@ApiResponse(code = 200, message = "Closest title found")
	})
	@PostMapping(value = "create-job")
	public ResponseEntity<JobTitles> create(
			@ApiParam("The job title to be saved")
			@RequestParam String input
	) throws NotFoundException {
		return ResponseEntity.ok(this.jobTitlesService.create(input));
	}

	@ApiOperation(value = "Delete job title",
			notes = "Delete previous saved job title",
			response = JobTitles.class,
			responseContainer = "ResponseEntity")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "No job title to delete"),
			@ApiResponse(code = 200, message = "Job title deleted")
	})
	@DeleteMapping(value = "delete-job")
	public ResponseEntity<JobTitles> delete(
			@ApiParam("The job title to be deleted")
			@RequestParam String input
	){
		return ResponseEntity.ok(this.jobTitlesService.remove(input));
	}

	@ApiOperation(value = "Update job title",
			notes = "Update previous saved job title",
			response = JobTitles.class,
			responseContainer = "ResponseEntity")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "No active job title found to update"),
			@ApiResponse(code = 200, message = "Job title updated")
	})
	@PatchMapping(value = "update-job")
	public ResponseEntity<JobTitles> update(
			@ApiParam("The request object with new and old title to be deleted")
			@RequestBody JobTitleRequest request
	) throws NotFoundException {
		return ResponseEntity.ok(this.jobTitlesService.update(request));
	}

	@ApiOperation(value = "Reactivate job title",
			notes = "Reactivate previous deleted job title",
			response = JobTitles.class,
			responseContainer = "ResponseEntity")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "No active job title founded to update"),
			@ApiResponse(code = 200, message = "Closest title found")
	})
	@PostMapping(value = "activate-job")
	public ResponseEntity<JobTitles> activate(
			@ApiParam("The title to be reactivated")
			@RequestParam String title) throws NotFoundException {
		return ResponseEntity.ok(this.jobTitlesService.activate(title));
	}

	@ApiOperation(value = "Search job titles",
			notes = "Search paginated list of job titles deleted or not",
			response = JobTitles.class,
			responseContainer = "ResponseEntity")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of paginated Job titles founded")
	})
	@GetMapping(value = "search-active-job")
	public ResponseEntity<Page<JobTitles>> searchActive(
			@ApiParam("The substring of a title to be founded")
			@RequestParam(required = false, defaultValue = "") String input,
			@ApiParam("The page to be retrieve")
			@RequestParam(defaultValue = "0") int page,
			@ApiParam("The number of objects to be returned by page")
			@RequestParam(defaultValue = "10") int size,
			@ApiParam("set true if also wants to return the deleted job titles")
			@RequestParam(defaultValue = "false") boolean getDeleted

	){
		return ResponseEntity.ok(this.jobTitlesService.search(input, page, size, getDeleted));
	}

}
