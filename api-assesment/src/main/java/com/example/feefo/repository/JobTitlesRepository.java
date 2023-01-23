package com.example.feefo.repository;

import com.example.feefo.models.JobTitles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobTitlesRepository extends JpaRepository<JobTitles,Long > {
    Optional<JobTitles> findByTitle(String title);

    Optional<JobTitles> findByTitleAndDeletedAtIsNull(String input);

    Page<JobTitles> findByTitleContaining(String input, Pageable pageable);

    Page<JobTitles> findByTitleContainingAndDeletedAtIsNull(String input, Pageable pageable);

    Optional<JobTitles> findByTitleAndDeletedAtIsNotNull(String title);
}
