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

        if (n < 1 || n > numbers.size()) {
            throw new IllegalArgumentException("Некорректный порядковый номер N");
        }
        return QuickSelect.findNthSmallest(numbers, n);
    }
}