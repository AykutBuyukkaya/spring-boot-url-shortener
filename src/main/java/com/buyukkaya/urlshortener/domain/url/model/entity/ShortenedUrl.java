package com.buyukkaya.urlshortener.domain.url.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

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
    @URL(message = "Only an URL can be shortened!")
    @Column(name = "actual_link", nullable = false)
    @Size(max = 64, message = "Maximum size of the link cannot be greater than 64!")
    private String realLink;

    @Column(name = "access_key", unique = true, nullable = false, updatable = false)
    @Size(min = 5, max = 16, message = "Url key has to be between {min} and {max} characters.")
    private String accessKey;

    @Column(name = "deletion_key", unique = true, nullable = false, updatable = false)
    private String deletionKey;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime validUntil;

}
