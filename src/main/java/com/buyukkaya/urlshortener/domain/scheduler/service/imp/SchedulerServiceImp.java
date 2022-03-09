package com.buyukkaya.urlshortener.domain.scheduler.service.imp;

import com.buyukkaya.urlshortener.domain.scheduler.service.SchedulerService;
import com.buyukkaya.urlshortener.domain.url.service.ShortenedUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerServiceImp implements SchedulerService {

    private final ShortenedUrlService shortenedUrlService;

    @Scheduled(initialDelayString = "${urlShortener.schedulerJobInitialDelay}",fixedDelayString = "${urlShortener.scheduledJobDelay}")
    @Override
    public void deletePastDateEntities() {

        Integer numOfDeletedEntities = shortenedUrlService.deleteEntitiesPastValidationDate();
        log.info("Scheduled job for deleting past entities successfully finished. Number of deleted entities = {}", numOfDeletedEntities);

    }
}
