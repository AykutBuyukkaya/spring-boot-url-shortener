package com.buyukkaya.urlshortener.domain.notification.service.imp;

import com.buyukkaya.urlshortener.domain.notification.model.request.MailSenderRequest;
import com.buyukkaya.urlshortener.domain.notification.service.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;



@Slf4j
@Service
public class MailSenderServiceImp implements MailSenderService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${urlShortener.mail.fromAddress}")
    private String fromAddress;

    @Value("${urlShortener.baseUrl}")
    private String baseUrl;

    @Value("${urlShortener.mail.templatePath}")
    private String mailTemplatePath;


    public MailSenderServiceImp(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = new TemplateEngine();
    }

    @Override
    public void sendCompletionMail(MailSenderRequest request) {
        try {
            final MimeMessage message = javaMailSender.createMimeMessage();
            final MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setText(templateEngine.process(readTemplate(), setMailContexts(request)), true);
            messageHelper.setSubject("URL Shortening complete!");
            messageHelper.setFrom(fromAddress);
            messageHelper.setTo(request.getMailAddress());


            javaMailSender.send(message);

        } catch (Exception e) {
            log.error("Error " + e);
            throw new MailSendException("Error while sending mail " + e.getMessage());
        }


    }

    private Context setMailContexts(MailSenderRequest request) {

        Context context = new Context();
        context.setVariable("shortenedUrl", baseUrl.concat(request.getAccessKey()));
        context.setVariable("actualUrl", request.getActualUrl());
        context.setVariable("deletionLink", baseUrl.concat(request.getDeletionKey()));
        context.setVariable("validUntil",  (request.getValidUntil() != null ) ?
                DateTimeFormatter.ofPattern("dd/MM/yyyy kk.mm").format(request.getValidUntil())
                : null);

        return context;
    }

    private String readTemplate() throws IOException {

        return Files.readString(new ClassPathResource(mailTemplatePath).getFile().toPath());


    }
}
