package com.buyukkaya.urlshortener.domain.url.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ShortenedUrlDto {

    private Long id;
    private String realLink;
    private String accessKey;
    private String deletionKey;
    private String createdAt;
    private String validUntil;

}
