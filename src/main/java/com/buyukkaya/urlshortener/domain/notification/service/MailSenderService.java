package com.buyukkaya.urlshortener.domain.notification.service;

import com.buyukkaya.urlshortener.domain.notification.model.request.MailSenderRequest;

public interface MailSenderService {

    void sendCompletionMail(MailSenderRequest request);

}
