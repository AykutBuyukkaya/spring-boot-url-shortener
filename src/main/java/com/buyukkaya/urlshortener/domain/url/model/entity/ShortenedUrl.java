package com.buyukkaya.urlshortener.domain.url.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "shortened_url")
public class ShortenedUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "actual_link", nullable = false)
    private String realLink;

    @Column(name = "access_key", unique = true, nullable = false, updatable = false)
    @Size(min = 5, max = 16, message = "Url key has to be between {min} and {max} characters.")
    private String accessKey;

    @Column(name = "deletion_key", unique = true, nullable = false, updatable = false)
    private String deletionKey;

    @DateTimeFormat(pattern = "dd/MM/yyyy kk.mm")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @DateTimeFormat(pattern = "dd/MM/yyyy kk.mm")
    @Column(nullable = true)
    private LocalDateTime validUntil;

}
