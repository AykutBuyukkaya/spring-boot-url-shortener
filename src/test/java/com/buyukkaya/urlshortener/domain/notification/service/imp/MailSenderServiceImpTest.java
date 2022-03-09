package com.buyukkaya.urlshortener.domain.notification.service.imp;

import com.buyukkaya.urlshortener.domain.notification.model.request.MailSenderRequest;
import com.buyukkaya.urlshortener.domain.notification.service.MailSenderService;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.util.ReflectionTestUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@RunWith(MockitoJUnitRunner.class)
class MailSenderServiceImpTest {

    private GreenMail greenMail;

    private JavaMailSenderImpl javaMailSender;

    private MailSenderService mailSenderServiceUnderTest;

    @BeforeEach
    void setUp() {

        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setPort(3025);
        javaMailSender.setHost("localhost");

        mailSenderServiceUnderTest = new MailSenderServiceImp(javaMailSender);

        ReflectionTestUtils.setField(mailSenderServiceUnderTest, "baseUrl", "https://xdd");
        ReflectionTestUtils.setField(mailSenderServiceUnderTest, "fromAddress", "mailTestClass@buyukkaya.com");

        greenMail = new GreenMail(ServerSetupTest.ALL);
        greenMail.start();

    }

    @AfterEach
    void clearGreenmail(){

        greenMail.stop();

    }

    @Test
    void sendCompletionMailSuccessfully() throws MessagingException {

        MailSenderRequest request = MailSenderRequest.builder()
                .mailAddress("testReceiver@receiver.com")
                .accessKey("abc")
                .deletionKey("xyz")
                .validUntil(LocalDateTime.now())
                .actualUrl("https://github.com/AykutBuyukkaya")
                .build();

        ReflectionTestUtils.setField(mailSenderServiceUnderTest, "mailTemplatePath", "/templates/email/CompletionMailTemplate.html");
        mailSenderServiceUnderTest.sendCompletionMail(request);

        Message[] messages = greenMail.getReceivedMessages();

        assertThat(messages).hasSize(1);
        assertThat(messages[0].getSubject()).isEqualTo("URL Shortening complete!");
        assertThat(messages[0].getFrom()).isEqualTo(new InternetAddress[]{new InternetAddress("mailTestClass@buyukkaya.com")});
        assertThat(messages[0].getRecipients(Message.RecipientType.TO)).isEqualTo(new InternetAddress[]{new InternetAddress("testReceiver@receiver.com")});

    }

    @Test
    void sendCompletionMail_readTemplateThrowsIOException() throws MessagingException {

        MailSenderRequest request = MailSenderRequest.builder()
                .mailAddress("testReceiver@receiver.com")
                .accessKey("abc")
                .deletionKey("xyz")
                .validUntil(LocalDateTime.now())
                .actualUrl("https://github.com/AykutBuyukkaya")
                .build();

        assertThatExceptionOfType(MailSendException.class).isThrownBy(() -> mailSenderServiceUnderTest.sendCompletionMail(request));

    }

    @Test
    void sendCompletionMailSuccessfullyWithValidUntilIsNull() throws MessagingException {

        MailSenderRequest request = MailSenderRequest.builder()
                .mailAddress("testReceiver@receiver.com")
                .accessKey("abc")
                .deletionKey("xyz")
                .actualUrl("https://github.com/AykutBuyukkaya")
                .build();

        ReflectionTestUtils.setField(mailSenderServiceUnderTest, "mailTemplatePath", "/templates/email/CompletionMailTemplate.html");
        mailSenderServiceUnderTest.sendCompletionMail(request);

        Message[] messages = greenMail.getReceivedMessages();

        assertThat(messages).hasSize(1);
        assertThat(messages[0].getSubject()).isEqualTo("URL Shortening complete!");
        assertThat(messages[0].getFrom()).isEqualTo(new InternetAddress[]{new InternetAddress("mailTestClass@buyukkaya.com")});
        assertThat(messages[0].getRecipients(Message.RecipientType.TO)).isEqualTo(new InternetAddress[]{new InternetAddress("testReceiver@receiver.com")});

    }
}