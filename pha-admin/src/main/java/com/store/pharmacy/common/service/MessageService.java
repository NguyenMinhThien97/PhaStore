package com.store.pharmacy.common.service;

import com.store.pharmacy.common.model.MessageOutput;

public interface MessageService {
    MessageOutput getByMessageCode(String messageCode);
}
