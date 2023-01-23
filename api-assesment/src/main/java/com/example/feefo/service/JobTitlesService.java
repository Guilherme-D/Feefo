package com.example.feefo.service;

import com.example.feefo.models.JobTitles;
import com.example.feefo.models.RequestEntity.JobTitleRequest;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;

public interface JobTitlesService {

    String getNormalizedTitle(String title) throws NotFoundException;

    JobTitles create(String title) throws NotFoundException;

    JobTitles remove(String title);

    JobTitles update(JobTitleRequest request) throws NotFoundException;

    Page<JobTitles> search(String title, int page, int size, boolean getDeleted);

    JobTitles activate(String title) throws NotFoundException;
}
