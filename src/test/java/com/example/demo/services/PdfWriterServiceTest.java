package com.example.demo.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PdfWriterServiceTest {
    
    @Autowired
    PdfWriterService service;

    @Test
    public void test() throws Exception {
        Map<String, Object> templateVariables = new HashMap<>();
        templateVariables.put("today", LocalDate.now());
        this.service.generatePdfFromHtml("pdf-example", "template-example", templateVariables);
    }

}
