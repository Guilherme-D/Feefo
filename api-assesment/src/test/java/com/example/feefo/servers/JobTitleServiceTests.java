package com.example.feefo.servers;

import com.example.feefo.models.JobTitles;
import com.example.feefo.repository.JobTitlesRepository;
import com.example.feefo.service.JobTitlesService;
import javassist.NotFoundException;
import org.assertj.core.api.AbstractDateAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.mockito.Mockito.when;

@SpringBootTest
class JobTitleServiceTests {

	@Autowired
	private JobTitlesService jobTitlesService;

	@Mock
	private JobTitlesRepository jobTitlesRepository;

	@Test
	void getNormalizeTitleShouldReturnIllegalArgumentExceptionIfTitleEmty() {
		when(this.jobTitlesRepository.findAll()).thenReturn(new ArrayList<>());
		Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> this.jobTitlesService.getNormalizedTitle(""))
				.isInstanceOf(IllegalArgumentException.class)
				.withMessage("Title cannot be empty or null");
	}

	@Test
	void getNormalizeTitleShouldReturnIllegalArgumentExceptionIfTitleNull() {
		when(this.jobTitlesRepository.findAll()).thenReturn(new ArrayList<>());
		Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> this.jobTitlesService.getNormalizedTitle(null))
				.isInstanceOf(IllegalArgumentException.class)
				.withMessage("Title cannot be empty or null");
	}

	@Test
	void getNormalizeTitleShouldReturnIllegalArgumentExceptionIfJobTitleIsEmpty() {
		when(this.jobTitlesRepository.findAll()).thenReturn(new ArrayList<>());
		Assertions.assertThatExceptionOfType(NotFoundException.class)
				.isThrownBy(() -> this.jobTitlesService.getNormalizedTitle("test"))
				.isInstanceOf(NotFoundException.class)
				.withMessage("No job title saved to normalize title");
	}

	@Test
	void getNormalizeTitleShouldReturnSameValue() throws NotFoundException {
		HashMap<String, String> testValues = new HashMap<>();
		testValues.put("Java engineer", "Software engineer");
		testValues.put("C# engineer", "Software engineer");
		testValues.put("Accountant", "Accountant");
		testValues.put("Chief Accountant", "Accountant");
		testValues.put("Architect", "Architect");
		testValues.put("Senior Architect", "Architect");


		Assertions.assertThat(this.jobTitlesService.create("Software engineer")).isNotNull();
		Assertions.assertThat(this.jobTitlesService.create("Accountant")).isNotNull();
		Assertions.assertThat(this.jobTitlesService.create("Architect")).isNotNull();

		testValues.forEach((input, answer) -> {
			String firstDistance = null;
			try {
				firstDistance = this.jobTitlesService.getNormalizedTitle(input);
			} catch (NotFoundException e) {
				e.printStackTrace();
			}

			Assertions.assertThat(firstDistance).isEqualTo(answer);

		});

	}

	@Test
	void getNormalizeTitleShouldReturnNotFound() {
		when(this.jobTitlesRepository.findAll()).thenReturn(new ArrayList<>());
		Assertions.assertThatExceptionOfType(NotFoundException.class)
				.isThrownBy(() -> this.jobTitlesService.getNormalizedTitle("test"))
				.isInstanceOf(NotFoundException.class)
				.withMessage("No job title saved to normalize title");
	}

	@Test
	void createJobTitleShouldReturnCreatedObject() throws NotFoundException {
		String title = "teste";
		JobTitles jobTitles = this.jobTitlesService.create(title);

		Assertions.assertThat(jobTitles).isNotNull();
		Assertions.assertThat(jobTitles.getJobId()).isEqualTo(1L);
		Assertions.assertThat(jobTitles.getTitle()).isEqualTo(title);
		Assertions.assertThat(jobTitles.getDeletedAt()).isNull();
		this.assertCreateDate(jobTitles);
	}

	@Test
	void createJobTitleShouldActivateDeletedJob() throws NotFoundException {
		String title = "teste";
		JobTitles jobTitles = this.jobTitlesService.create(title);
		JobTitles remove = this.jobTitlesService.remove(title);

		Assertions.assertThat(jobTitles).isNotNull();
		Assertions.assertThat(jobTitles.getJobId()).isEqualTo(1L);
		Assertions.assertThat(jobTitles.getTitle()).isEqualTo(title);
		Assertions.assertThat(jobTitles.getDeletedAt()).isNull();
		this.assertCreateDate(remove);
		this.assertDeleteDate(remove);
	}

	@Test
	void createJobTitleShouldReturnRuntimeException() throws NotFoundException {
		String title = "title";
		this.jobTitlesService.create(title);
		Assertions.assertThatExceptionOfType(RuntimeException.class)
				.isThrownBy(() -> this.jobTitlesService.create(title))
				.withMessage("Job title "+title+" already exists");
	}

	private void assertCreateDate(JobTitles jobTitles) {
		Assertions.assertThat(jobTitles.getCreatedAt()).isInSameDayAs(new Date());
		Assertions.assertThat(jobTitles.getCreatedAt()).isInSameMonthAs(new Date());
		Assertions.assertThat(jobTitles.getCreatedAt()).isInSameYearAs(new Date());
	}
	private void assertDeleteDate(JobTitles jobTitles) {
		Assertions.assertThat(jobTitles.getDeletedAt()).isInSameDayAs(new Date());
		Assertions.assertThat(jobTitles.getDeletedAt()).isInSameMonthAs(new Date());
		Assertions.assertThat(jobTitles.getDeletedAt()).isInSameYearAs(new Date());
	}


}
