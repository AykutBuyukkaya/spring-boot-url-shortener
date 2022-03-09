package com.buyukkaya.urlshortener.domain.notification.model.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailSenderRequest {

    @NonNull
    private String mailAddress;

    @NonNull
    private String actualUrl;

    @NonNull
    private String accessKey;

    @NonNull
    private String deletionKey;

    private LocalDateTime validUntil;

}
