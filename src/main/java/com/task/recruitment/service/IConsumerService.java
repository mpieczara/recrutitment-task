package com.task.recruitment.service;

import com.task.recruitment.model.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IConsumerService {

    Optional<Data> getDataById(Long id);

    Optional<List<Data>> getDataTimePeriod(LocalDate startDate, LocalDate endDate, Integer pageNo, Integer pageSize);
}
