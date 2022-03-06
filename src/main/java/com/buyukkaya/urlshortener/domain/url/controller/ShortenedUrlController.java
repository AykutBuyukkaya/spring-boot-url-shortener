package com.buyukkaya.urlshortener.domain.url.controller;

import com.buyukkaya.urlshortener.domain.common.model.response.ApiResponse;
import com.buyukkaya.urlshortener.domain.url.model.request.UrlCreationRequest;
import com.buyukkaya.urlshortener.domain.url.service.ShortenedUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/url-shortener")
@RequiredArgsConstructor
public class ShortenedUrlController {

    private final ShortenedUrlService shortenedUrlService;

    @PostMapping
    public ResponseEntity<ApiResponse> createShortUrl(@RequestBody @Valid UrlCreationRequest urlCreationRequest){
        return shortenedUrlService.createUrlEntity(urlCreationRequest);
    }

    @GetMapping(path = "/{accessKey}")
    public RedirectView accessActualUrl(@PathVariable("accessKey") String accessKey){
        return shortenedUrlService.forwardToActualUrl(accessKey);
    }

    @DeleteMapping(path = "/{deletionKey}")
    public ResponseEntity<ApiResponse> deleteShortenedUrl(@PathVariable("deletionKey") String deletionKey){
        return shortenedUrlService.deleteShortenedUrl(deletionKey);
    }

}
