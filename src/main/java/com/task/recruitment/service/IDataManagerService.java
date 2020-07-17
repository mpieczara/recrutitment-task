package com.task.recruitment.service;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface IDataManagerService {

    void deleteClientDataById(Long id);

    void validateClientData(List<CSVRecord> csvRecords);
}
