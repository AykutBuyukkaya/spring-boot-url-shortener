package com.buyukkaya.urlshortener.domain.url.service.imp;

import com.buyukkaya.urlshortener.domain.common.model.response.ApiResponse;
import com.buyukkaya.urlshortener.domain.notification.model.request.MailSenderRequest;
import com.buyukkaya.urlshortener.domain.notification.service.MailSenderService;
import com.buyukkaya.urlshortener.domain.url.model.entity.ShortenedUrl;
import com.buyukkaya.urlshortener.domain.url.model.request.UrlCreationRequest;
import com.buyukkaya.urlshortener.domain.url.repository.ShortenedUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UrlShortenerServiceImpTest {


    @Mock
    private MailSenderService mailSenderService;

    @Mock
    private ShortenedUrlRepository shortenedUrlRepository;

    @InjectMocks
    private UrlShortenerServiceImp urlShortenerServiceImp;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    void createUrlEntityWithSuccess_RequestContainsAccessKeyAndValidUntil() {

        UrlCreationRequest urlCreationRequest = new UrlCreationRequest("https://github.com/AykutBuyukkaya",
                "testing",
                LocalDateTime.now().plusMinutes(5),
                "testing@buyukkaya.com"
        );

        when(shortenedUrlRepository.findByAccessKey(anyString())).thenReturn(Optional.empty());

        assertThat(urlShortenerServiceImp.createUrlEntity(urlCreationRequest)).isEqualTo(new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Url successfully shortened!")
                        .build(),
                HttpStatus.OK
        ));

        verify(mailSenderService, times(1)).sendCompletionMail(any(MailSenderRequest.class));
        verify(shortenedUrlRepository, times(1)).save(any(ShortenedUrl.class));

    }

    @Test
    void createUrlEntityWithSuccess_RequestDoesNotContainAccessKeyAndValidUntil() {

        UrlCreationRequest urlCreationRequest = new UrlCreationRequest("https://github.com/AykutBuyukkaya",
                null,
                null,
                "testing@buyukkaya.com"
        );

        when(shortenedUrlRepository.findByAccessKey(anyString())).thenReturn(Optional.empty());

        assertThat(urlShortenerServiceImp.createUrlEntity(urlCreationRequest)).isEqualTo(new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Url successfully shortened!")
                        .build(),
                HttpStatus.OK
        ));

        verify(mailSenderService, times(1)).sendCompletionMail(any(MailSenderRequest.class));
        verify(shortenedUrlRepository, times(1)).save(any(ShortenedUrl.class));

    }


    @Test
    void forwardToActualUrl() {
    }

    @Test
    void deleteShortenedUrl() {
    }

    @Test
    void deleteEntitiesPastValidationDate() {
    }
}