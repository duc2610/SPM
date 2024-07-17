package com.swp391.SPM.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.swp391.SPM.dto.YeuCauThueDTO;
import com.swp391.SPM.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class PdfExportService {
    @Autowired
    private TemplateEngine templateEngine;

    public ByteArrayInputStream exportBookingsToPdf(YeuCauThueDTO bookings, User user) throws IOException {
        // Context object to hold data
        Context context = new Context();
        context.setVariable("items", bookings);
        context.setVariable("user", user);
        // Process template
        String html = templateEngine.process("invoice-template", context);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(html, outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
