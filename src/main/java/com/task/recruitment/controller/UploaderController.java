package com.task.recruitment.controller;

import com.task.recruitment.service.IUploaderService;
import com.task.recruitment.utils.FileUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploaderController {

    private IUploaderService uploaderService;

    public UploaderController(IUploaderService uploaderService) {
        this.uploaderService = uploaderService;
    }

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadDataFile(@RequestParam("file") MultipartFile file) {
        if(FileUtil.hasCorrectFormat(file)) {
            uploaderService.saveCSVFile(file);
            return ResponseEntity.status(HttpStatus.OK).body("Data added successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong file format");
    }
}
