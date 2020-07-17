package com.task.recruitment.service;

import com.task.recruitment.model.Data;
import com.task.recruitment.repository.DataRepository;
import com.task.recruitment.utils.FileUtil;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataManagerService implements IDataManagerService {

    private DataRepository dataRepository;

    public DataManagerService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void deleteClientDataById(Long id) {
        Optional<Data> dataToRemove = dataRepository.findById(id);
        dataToRemove.ifPresent(row -> dataRepository.delete(row));
    }

    public void validateClientData(List<CSVRecord> csvRecords) {
        for(CSVRecord record : csvRecords) {
            if(!FileUtil.validateSingleRecord(record)) {
                csvRecords.remove(record);
            }
        }
    }
}
