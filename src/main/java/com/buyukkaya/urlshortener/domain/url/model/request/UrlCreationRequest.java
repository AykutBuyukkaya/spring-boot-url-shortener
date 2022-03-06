package com.buyukkaya.urlshortener.domain.url.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlCreationRequest {

    @URL(message = "Url is not valid!")
    private String realUrl;

    @Size(min = 5, max = 16, message = "Url key has to be between {min} and {max} characters!")
    @Nullable
    private String accessKey;

    @JsonFormat(pattern = "dd/MM/yyyy kk.mm")
    private LocalDateTime validUntil;

    @Email(message = "Invalid user mail")
    private String mail;

}
