package com.task.recruitment.utils;

import com.task.recruitment.model.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final String TYPE = "text/csv";
    private static final String[] FILE_HEADER = { "PRIMARY_KEY", "NAME", "DESCRIPTION", "UPDATED_TIMESTAMP" };
    private static final int SIZE = 4;

    private FileUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Data> parseFileDataToEntity(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Data> clientData = new ArrayList<>();

            List<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                if(!validateSingleRecord(csvRecord)) {
                    continue;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Data data = new Data(
                        Long.parseLong(csvRecord.get(FILE_HEADER[0])),
                        csvRecord.get(FILE_HEADER[1]),
                        csvRecord.get(FILE_HEADER[2]),
                        LocalDate.parse(csvRecord.get(FILE_HEADER[3]), formatter)
                );
                clientData.add(data);
            }
            return clientData;

        } catch (IOException e) {
            throw new RuntimeException("Error during getting the data from file: " + e.getMessage());
        }
    }

    public static boolean hasCorrectFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static boolean validateSingleRecord(CSVRecord record) {
        return (record.size() == SIZE || isIdNumeric(record.get(FILE_HEADER[0])));
    }

    public static boolean isIdNumeric(String id) {
        if (id == null) {
            return false;
        }
        try {
            Long.parseLong(id);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
