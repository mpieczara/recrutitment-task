package com.task.recruitment.controller;

import com.task.recruitment.model.Data;
import com.task.recruitment.service.IConsumerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data")
public class ConsumerController {

    private IConsumerService consumerService;

    public ConsumerController(IConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/load/{id}")
    public ResponseEntity<Optional<Data>> getDataById(@PathVariable("id") Long id) {
        Optional<Data> clientData = consumerService.getDataById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientData);
    }

    @GetMapping("/load")
    public ResponseEntity<Optional<List<Data>>> getDataByTimePeriod(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate endDate,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize
            ) {
        Optional<List<Data>> clientData = consumerService.getDataTimePeriod(startDate, endDate, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(clientData);
    }
}
