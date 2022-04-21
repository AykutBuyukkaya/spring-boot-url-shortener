package com.buyukkaya.urlshortener.domain.scheduler.service.imp;

import com.buyukkaya.urlshortener.domain.scheduler.service.SchedulerService;
import com.buyukkaya.urlshortener.domain.url.service.UrlShortenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
public class SchedulerServiceImp implements SchedulerService {

    private final UrlShortenerService shortenedUrlService;

    public SchedulerServiceImp(UrlShortenerService shortenedUrlService) {
        this.shortenedUrlService = shortenedUrlService;
    }

    @Scheduled(initialDelayString = "${urlShortener.schedulerJobInitialDelay}",fixedDelayString = "PT5M")
    @Override
    public void deletePastDateEntities() {

        Integer numOfDeletedEntities = shortenedUrlService.deleteEntitiesPastValidationDate();
        log.info("Scheduled job for deleting past entities successfully finished. Number of deleted entities = {}", numOfDeletedEntities);

    }
}
