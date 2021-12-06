package com.store.pharmacy.common.service.impl;

import com.store.pharmacy.common.service.SendMailService;
import com.store.pharmacy.config.SendGridEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {

    private final SendGridEmailService sendGridEmailService;

    @Override
    public boolean sendMail(String subject, String templateName, String mailReceiver) {
        final Locale locale = Locale.getDefault();

        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("idGiaoDich", "12345567");
        ctx.setVariable("soTien", "24.000");
        ctx.setVariable("tenKhachHang", "Nguyễn Minh Thiên");
        ctx.setVariable("tenSanPham", "Áo quần");
        ctx.setVariable("sanPhams", "Áo quần");
        ctx.setVariable("soLuong", "1");
        ctx.setVariable("thanhTien", "24.000");
        ctx.setVariable("tienPayPal", "24.000");
        sendGridEmailService.sendEmail(templateName, subject, mailReceiver, ctx);
        return true;
    }
}
