package com.task.recruitment.service;

import com.task.recruitment.model.Data;
import com.task.recruitment.repository.DataRepository;
import com.task.recruitment.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UploaderService implements IUploaderService {

    private DataRepository dataRepository;

    public UploaderService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void saveCSVFile(MultipartFile file) {
        try {
            List<Data> data = FileUtil.parseFileDataToEntity(file.getInputStream());
            dataRepository.saveAll(data);
        } catch (IOException e) {
            throw new RuntimeException("Error during getting the data from file: " + e.getMessage());
        }
    }
}
