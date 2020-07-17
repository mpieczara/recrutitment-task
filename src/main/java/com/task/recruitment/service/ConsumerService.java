package com.task.recruitment.service;

import com.task.recruitment.model.Data;
import com.task.recruitment.repository.DataRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService implements IConsumerService {

    private DataRepository dataRepository;

    public ConsumerService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public Optional<Data> getDataById(Long id) {
        return dataRepository.findById(id);
    }

    public Optional<List<Data>> getDataTimePeriod(LocalDate startDate, LocalDate endDate, Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        return dataRepository.findAllByTimestampBetween(startDate, endDate, paging);
    }
}
