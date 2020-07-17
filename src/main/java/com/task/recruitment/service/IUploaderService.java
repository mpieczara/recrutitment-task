package com.task.recruitment.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploaderService {

    void saveCSVFile(MultipartFile file);
}
