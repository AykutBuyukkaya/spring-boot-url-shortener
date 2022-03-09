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

    @Size(max = 32, message = "Size of the actual URL cannot be greater than {max} characters")
    @URL(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "Invalid URL!")
    private String realUrl;

    @Size(min = 5, max = 16, message = "Url key has to be between {min} and {max} characters!")
    @Nullable
    private String accessKey;

    @JsonFormat(pattern = "dd/MM/yyyy kk.mm")
    private LocalDateTime validUntil;

    @Email(message = "Invalid user mail")
    private String mail;

}
