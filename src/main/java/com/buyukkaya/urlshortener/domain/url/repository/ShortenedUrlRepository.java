package com.buyukkaya.urlshortener.domain.url.repository;

import com.buyukkaya.urlshortener.domain.url.model.entity.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, Long> {

    Optional<ShortenedUrl> findByAccessKey(String accessKey);

    @Modifying
    @Query(value = "DELETE FROM ShortenedUrl url WHERE url.deletionKey = :deletion_key")
    void deleteByDeletionKey(@Param("deletion_key") String deletionKey);

    boolean existsByDeletionKey(String deletionKey);

    Integer deleteByValidUntilLessThanEqual(LocalDateTime dateTime);

}
