package com.buyukkaya.urlshortener.domain.url.service;

import com.buyukkaya.urlshortener.domain.common.model.response.ApiResponse;
import com.buyukkaya.urlshortener.domain.url.model.request.UrlCreationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

public interface ShortenedUrlService {

    ResponseEntity<ApiResponse> createUrlEntity(UrlCreationRequest request);

    RedirectView forwardToActualUrl(String accessKey);

    ResponseEntity<ApiResponse> deleteShortenedUrl(String deletionKey);

    Integer deleteEntitiesPastValidationDate();

}
