package com.store.pharmacy.config;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.store.pharmacy.common.model.SenderInfo;
import com.store.pharmacy.common.repository.SenderInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendGridEmailService {
    private static final String SEND_EMAIL_ENDPOINT = "mail/send";
    private final String SENDER_TYPE_ADMIN = "1";

    private final SpringMailConfig springMailConfig;
    private final SenderInfoRepository senderInfoRepository;

    public void sendEmail(String templateName, String subject, String mailReceiver, Context ctx) {
        List<SenderInfo> senderInfoList = senderInfoRepository.findBySenderTypeAndEnabledTrue(SENDER_TYPE_ADMIN);
        if(!senderInfoList.isEmpty()){
            String sendgridApiKey = senderInfoList.get(0).getSenderAPIKey();
            String mailSender = senderInfoList.get(0).getMailSender();
            // Create the HTML body using Thymeleaf
            final String htmlContent = this.springMailConfig.emailTemplateEngine().process(templateName, ctx);

            Content content = new Content("text/html", htmlContent);

            Mail mail = new Mail(new Email(mailSender), subject, new Email(mailReceiver), content);

            SendGrid sg = new SendGrid(sendgridApiKey);
            try {
                Request request = new Request();

                request.setMethod(Method.POST);
                request.setEndpoint(SEND_EMAIL_ENDPOINT);
                request.setBody(mail.build());

                Response response = sg.api(request);

                System.out.println(response.getStatusCode());
                System.out.println(response.getHeaders());
                System.out.println(response.getBody());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}
