package com.buyukkaya.urlshortener.domain.notification.model.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
class MailSenderRequestTest {

    MailSenderRequest mailSenderRequest;

    @BeforeEach
    void setUp() {

        mailSenderRequest = MailSenderRequest.builder()
                .mailAddress("buyukkaya@buyukkaya.com")
                .actualUrl("https://actual.url")
                .deletionKey("delete")
                .accessKey("access")
                .validUntil(LocalDateTime.of(2022, 8, 15, 15, 15))
                .build();

    }

    @Test
    void getMailAddress() {

        assertThat(mailSenderRequest.getMailAddress()).isEqualTo("buyukkaya@buyukkaya.com");

    }

    @Test
    void getActualUrl() {

        assertThat(mailSenderRequest.getActualUrl()).isEqualTo("https://actual.url");

    }

    @Test
    void getAccessKey() {

        assertThat(mailSenderRequest.getAccessKey()).isEqualTo("access");

    }

    @Test
    void getDeletionKey() {

        assertThat(mailSenderRequest.getDeletionKey()).isEqualTo("delete");

    }

    @Test
    void getValidUntil() {

        assertThat(mailSenderRequest.getValidUntil()).isEqualTo(LocalDateTime.of(2022, 8, 15, 15, 15));
    }

    @Test
    void setMailAddress() {

        mailSenderRequest.setMailAddress("abc@mail.com");

        assertThat(mailSenderRequest.getMailAddress()).isEqualTo("abc@mail.com");

    }

    @Test
    void setActualUrl() {

        mailSenderRequest.setActualUrl("https://abc.com");

        assertThat(mailSenderRequest.getActualUrl()).isEqualTo("https://abc.com");

    }

    @Test
    void setAccessKey() {

        mailSenderRequest.setAccessKey("accessKey");

        assertThat(mailSenderRequest.getAccessKey()).isEqualTo("accessKey");

    }

    @Test
    void setDeletionKey() {

        mailSenderRequest.setDeletionKey("deletionKey");

        assertThat(mailSenderRequest.getDeletionKey()).isEqualTo("deletionKey");

    }

    @Test
    void setValidUntil() {

        mailSenderRequest.setValidUntil(LocalDateTime.of(2022, Month.APRIL, 15,15,15));

        assertThat(mailSenderRequest.getValidUntil()).isEqualTo(LocalDateTime.of(2022, Month.APRIL, 15,15,15));

    }

    @Test
    void testEquals() {

        MailSenderRequest result = MailSenderRequest.builder()
                .mailAddress("buyukkaya@buyukkaya.com")
                .actualUrl("https://actual.url")
                .deletionKey("delete")
                .accessKey("access")
                .validUntil(LocalDateTime.of(2022, 8, 15, 15, 15))
                .build();

        assertThat(mailSenderRequest.equals(result)).isTrue();

    }

    @Test
    void canEqual() {

        MailSenderRequest result = MailSenderRequest.builder()
                .mailAddress("asda@mail.com")
                .accessKey("asdas")
                .deletionKey("asdsa")
                .actualUrl("https://xd.com")
                .build();

        assertThat(mailSenderRequest.canEqual(result)).isTrue();

    }

    @Test
    void testHashCode() {

        assertThat(mailSenderRequest.hashCode()).isEqualTo(
                MailSenderRequest.builder()
                .mailAddress("buyukkaya@buyukkaya.com")
                .actualUrl("https://actual.url")
                .deletionKey("delete")
                .accessKey("access")
                .validUntil(LocalDateTime.of(2022, 8, 15, 15, 15))
                .build().hashCode());

    }

    @Test
    void testToString() {


        assertThat(mailSenderRequest.toString()).hasToString("MailSenderRequest(mailAddress=buyukkaya@buyukkaya.com" +
                ", actualUrl=https://actual.url" +
                ", accessKey=access" +
                ", deletionKey=delete" +
                ", validUntil=2022-08-15T15:15)");

    }

    @Test
    void builder() {


        assertThat(mailSenderRequest).isEqualTo( MailSenderRequest.builder()
                .mailAddress("buyukkaya@buyukkaya.com")
                .actualUrl("https://actual.url")
                .deletionKey("delete")
                .accessKey("access")
                .validUntil(LocalDateTime.of(2022, 8, 15, 15, 15))
                .build());

    }
}