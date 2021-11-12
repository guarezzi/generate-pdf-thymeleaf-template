package com.example.demo.services;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Service
public class PdfWriterService {
    
    @Autowired
    private SpringTemplateEngine templateEngine;

    private String parseThymeleafTemplate(String templateName, Map<String, Object> templateVariables) {
        Context context = new Context(Locale.getDefault());
        context.setVariables(templateVariables);
        return templateEngine.process(templateName, context);
    }

    public OutputStream generatePdfFromHtml(final String outputFileName, final String templateName,final Map<String, Object> templateVariables) throws Exception {
        OutputStream outputStream = new FileOutputStream(String.format("%s.pdf", outputFileName));
    
        String content = this.parseThymeleafTemplate(templateName, templateVariables);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(content);
        renderer.layout();
        renderer.createPDF(outputStream);
    
        outputStream.close();
        return outputStream;
    }
}
