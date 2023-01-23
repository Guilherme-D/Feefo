package com.example.feefo.servers;

import com.example.feefo.models.JobTitles;
import com.example.feefo.repository.JobTitlesRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobTitleRepositoryTests {

	@Autowired
	private JobTitlesRepository jobTitlesRepository;

	@Test
	void saveJobTitleShouldNotReturnNull() {
		JobTitles jobSaved = this.jobTitlesRepository.save(new JobTitles("teste"));
		Assertions.assertThat(jobSaved).isNotNull();
		Assertions.assertThat(jobSaved.getJobId()).isPositive();
		Assertions.assertThat(jobSaved.getTitle()).isNotNull();
		Assertions.assertThat(jobSaved.getTitle()).isNotBlank();
		Assertions.assertThat(jobSaved.getTitle()).isNotEmpty();
		Assertions.assertThat(jobSaved.getDeletedAt()).isNull();
		Assertions.assertThat(jobSaved.getCreatedAt()).isNotNull();
		Assertions.assertThat(jobSaved.getUpdatedAt()).isNull();
	}

}
