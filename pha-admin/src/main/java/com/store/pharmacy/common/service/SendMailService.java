package com.store.pharmacy.common.service;

public interface SendMailService {
    boolean sendMail(String subject, String templateName, String mailReceiver);
}
