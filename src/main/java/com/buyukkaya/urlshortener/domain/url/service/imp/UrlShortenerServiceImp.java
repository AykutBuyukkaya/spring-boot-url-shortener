package com.buyukkaya.urlshortener.domain.url.service.imp;

import com.buyukkaya.urlshortener.domain.common.model.mapper.ShortenedUrlMapper;
import com.buyukkaya.urlshortener.domain.common.model.response.ApiResponse;
import com.buyukkaya.urlshortener.domain.notification.model.request.MailSenderRequest;
import com.buyukkaya.urlshortener.domain.notification.service.MailSenderService;
import com.buyukkaya.urlshortener.domain.url.model.entity.ShortenedUrl;
import com.buyukkaya.urlshortener.domain.url.model.exception.AccessKeyExistException;
import com.buyukkaya.urlshortener.domain.url.model.exception.ShortenedUrlNotFoundException;
import com.buyukkaya.urlshortener.domain.url.model.request.UrlCreationRequest;
import com.buyukkaya.urlshortener.domain.url.repository.ShortenedUrlRepository;
import com.buyukkaya.urlshortener.domain.url.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlShortenerServiceImp implements UrlShortenerService {

    private final ShortenedUrlRepository shortenedUrlRepository;
    private final MailSenderService mailSenderService;


    //TODO: WRITE EXCEPTION HANDLER.

    @Override
    public ResponseEntity<ApiResponse> createUrlEntity(UrlCreationRequest request) {

        try {

            String accessKey = (request.getAccessKey() == null)
                    ?
                    RandomStringUtils.randomAlphanumeric(new Random().nextInt(5) + 2)
                    :
                    request.getAccessKey();

            if (shortenedUrlRepository.findByAccessKey(request.getAccessKey()).isPresent()) {
                throw new AccessKeyExistException("Given access key already exists!");
            }


            String deletionKey = RandomStringUtils.randomAlphanumeric(new Random().nextInt(5) + 2);

            ShortenedUrl shortenedUrl = ShortenedUrl.builder()
                    .accessKey((request.getAccessKey() == null) ? accessKey : request.getAccessKey())
                    .deletionKey(deletionKey)
                    .realLink(request.getRealUrl())
                    .createdAt(LocalDateTime.now())
                    .validUntil(request.getValidUntil())
                    .build();


            mailSenderService.sendCompletionMail(MailSenderRequest.builder()
                    .accessKey(accessKey)
                    .deletionKey(deletionKey)
                    .actualUrl(request.getRealUrl())
                    .validUntil(request.getValidUntil())
                    .mailAddress(request.getMail())
                    .build());

            shortenedUrlRepository.save(shortenedUrl);

            return new ResponseEntity<>(ApiResponse.builder()
                    .message("Url successfully shortened!")
                    .build(), HttpStatus.OK);

        } catch (Exception e) {

            log.error("Error while creating shortened url: {}", e);

            return new ResponseEntity<>(ApiResponse.builder()
                    .message(e.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }


    }

    @Override
    public RedirectView forwardToActualUrl(String accessKey) {

        return new RedirectView(shortenedUrlRepository.findByAccessKey(accessKey)
                .orElseThrow(
                        () -> new ShortenedUrlNotFoundException("We couldn't find a data according to given access key.")
                ).getRealLink());
    }

    @Transactional
    @Override
    public ResponseEntity<ApiResponse> deleteShortenedUrl(String deletionKey) {


        try {

            if (shortenedUrlRepository.existsByDeletionKey(deletionKey)) {
                throw new AccessKeyExistException("Given access key already exist!");
            }

            shortenedUrlRepository.deleteByDeletionKey(deletionKey);

            return new ResponseEntity<>(ApiResponse.builder()
                    .message("Entity deleted successfully.")
                    .build(), HttpStatus.OK);


        } catch (Exception e) {

            log.error(e.getMessage());

            return new ResponseEntity<>(ApiResponse.builder()
                    .message(e.getMessage())
                    .build()
                    , HttpStatus.BAD_REQUEST);

        }


    }

    @Transactional
    @Override
    public Integer deleteEntitiesPastValidationDate() {

        return shortenedUrlRepository.deleteByValidUntilLessThanEqual(LocalDateTime.now());

    }

    @Override
    public ResponseEntity<ApiResponse> getShortenerUrlById(Long id) {
        return new ResponseEntity<>(ApiResponse.builder()
                .data(ShortenedUrlMapper.MAPPER.toShortenedUrlDto(shortenedUrlRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("ShortenedUrl with id"))))
                .message("Shortened url entity with given id is found")
                .build(),
                HttpStatus.OK);
    }
}
