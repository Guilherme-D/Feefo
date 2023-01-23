package com.example.feefo.service.impl;

import com.example.feefo.models.JobTitles;
import com.example.feefo.models.RequestEntity.JobTitleRequest;
import com.example.feefo.models.dto.NormalizedTitle;
import com.example.feefo.repository.JobTitlesRepository;
import com.example.feefo.service.JobTitlesService;
import com.example.feefo.service.LevenshteinService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobTitlesImpl implements JobTitlesService {

    @Autowired
    private LevenshteinService levenshteinService;

    @Autowired
    private JobTitlesRepository jobTitlesRepository;

    @Override
    public String getNormalizedTitle(String title) throws NotFoundException {
        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty or null");
        }
        List<JobTitles> jobTitles = this.jobTitlesRepository.findAll();

        if(jobTitles.isEmpty()){
            throw new NotFoundException("No job title saved to normalize title");
        }

        NormalizedTitle normalizedTitle = jobTitles.stream().map(job ->
                        new NormalizedTitle(job.getTitle(), this.levenshteinService.calculateDistance(title, job.getTitle()))
                ).min(Comparator.comparing(NormalizedTitle::getDistance)).get();

        return normalizedTitle.getTitle();
    }

    @Override
    public JobTitles create(String title) throws NotFoundException {
        Optional<JobTitles> jobTitle = this.jobTitlesRepository.findByTitle(title);

        if(jobTitle.isPresent()){
            JobTitles existent = jobTitle.get();
            if(existent.getDeletedAt() != null){
                return this.activate(existent.getTitle());
            }
            throw new RuntimeException("Job title "+title+" already exists");
        }

        return this.jobTitlesRepository.save(new JobTitles(title));
    }

    @Override
    public JobTitles remove(String title) {
        JobTitles jobTitle = this.jobTitlesRepository.findByTitleAndDeletedAtIsNull(title)
                .orElseThrow(() -> new NoSuchElementException("No job title with name "+title+" exists"));

        jobTitle.setDeletedAt(new Date());

        return this.jobTitlesRepository.save(jobTitle);
    }

    @Override
    public JobTitles update(JobTitleRequest request) throws NotFoundException {
        JobTitles jobTitle = this.jobTitlesRepository.findByTitleAndDeletedAtIsNull(request.getTitle())
                .orElseThrow(() -> new NotFoundException("Job title "+request.getTitle()+" does not exists or is deleted."));
        
        jobTitle.setTitle(request.getNewTitle());
        jobTitle.setUpdatedAt(new Date());

        return this.jobTitlesRepository.save(jobTitle);
    }

    @Override
    public Page<JobTitles> search(String input, int page, int size, boolean getDeleted) {
        Pageable pageable = PageRequest.of(page, size);
        return getDeleted ? this.jobTitlesRepository.findByTitleContaining(input, pageable) :
                this.jobTitlesRepository.findByTitleContainingAndDeletedAtIsNull(input, pageable);
    }

    @Override
    public JobTitles activate(String title) throws NotFoundException {
        JobTitles jobTitle = this.jobTitlesRepository.findByTitleAndDeletedAtIsNotNull(title)
                .orElseThrow(() -> new NotFoundException("Job title "+title+" does not exists or is not deleted."));

        jobTitle.setDeletedAt(null);
        jobTitle.setUpdatedAt(new Date());
        return this.jobTitlesRepository.save(jobTitle);
    }
}
