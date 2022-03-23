package com.buyukkaya.urlshortener.domain.scheduler.service.imp;

import com.buyukkaya.urlshortener.domain.url.service.UrlShortenerService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class SchedulerServiceImpTest {

    @InjectMocks
    private SchedulerServiceImp schedulerServiceImp;

    @Mock
    private UrlShortenerService urlShortenerService;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.initMocks(this);

    }

    @Test
    void deletePastDateEntities() {

        when(urlShortenerService.deleteEntitiesPastValidationDate()).thenReturn(0);

        schedulerServiceImp.deletePastDateEntities();

        await().atMost(Duration.ofMinutes(10)).untilAsserted(() -> verify(urlShortenerService, times(1)).deleteEntitiesPastValidationDate());

    }
}