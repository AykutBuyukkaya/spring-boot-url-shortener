package com.buyukkaya.urlshortener.domain.common.model.mapper;

import com.buyukkaya.urlshortener.domain.url.model.dto.ShortenedUrlDto;
import com.buyukkaya.urlshortener.domain.url.model.entity.ShortenedUrl;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShortenedUrlMapper {

    ShortenedUrlMapper MAPPER = Mappers.getMapper(ShortenedUrlMapper.class);

    ShortenedUrlDto toShortenedUrlDto(ShortenedUrl shortenedUrl);

}
