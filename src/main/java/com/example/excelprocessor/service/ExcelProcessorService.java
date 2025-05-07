package com.example.excelprocessor.service;

import com.example.excelprocessor.util.ExcelReader;
import com.example.excelprocessor.util.QuickSelect;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelProcessorService {

    private final ExcelReader excelReader;

    public ExcelProcessorService(ExcelReader excelReader) {
        this.excelReader = excelReader;
    }

    public Integer findNthSmallest(MultipartFile file, int n) throws IOException {

        List<Integer> numbers = excelReader.readNumbersFromFirstColumn(file);
        return QuickSelect.findNthSmallest(numbers, n);
    }
}