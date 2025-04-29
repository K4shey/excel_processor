package com.example.excelprocessor.controller;

import com.example.excelprocessor.service.ExcelProcessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Tag(name = "Excel Processor API", description = "Обработка содержимого Excel-файла")
public class ExcelProcessorController {

    private final ExcelProcessorService excelProcessorService;

    public ExcelProcessorController(ExcelProcessorService excelProcessorService) {
        this.excelProcessorService = excelProcessorService;
    }

    @PostMapping(value = "/findNthMin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Найти N-ное минимальное число",
            description = "Загрузите Excel-файл с числами в первом столбце и укажите N."
    )
    public ResponseEntity<Integer> findNthSmallest(
            @Parameter(
                    description = "Excel-файл (.xlsx)",
                    required = true,
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart("file") MultipartFile file,

            @Parameter(
                    description = "Порядковый номер минимального числа (например, 1 для самого маленького)",
                    required = true
            )
            @RequestParam("n") int n
    ) throws IOException {
        Integer minValue = excelProcessorService.findNthSmallest(file, n);
        return ResponseEntity.ok(minValue);
    }
}