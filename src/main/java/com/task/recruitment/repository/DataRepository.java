package com.task.recruitment.repository;

import com.task.recruitment.model.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DataRepository extends PagingAndSortingRepository<Data, Long> {
    Optional<List<Data>> findAllByTimestampBetween(LocalDate start, LocalDate end, Pageable pageable);
}
