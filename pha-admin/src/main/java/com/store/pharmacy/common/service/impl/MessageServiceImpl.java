package com.store.pharmacy.common.service.impl;

import com.store.pharmacy.common.model.Message;
import com.store.pharmacy.common.model.MessageOutput;
import com.store.pharmacy.common.repository.MessageRepository;
import com.store.pharmacy.common.service.MessageService;
import com.store.pharmacy.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public MessageOutput getByMessageCode(String messageCode, String lang) {
        Message message = messageRepository.findByMessageCodeAndLangAndEnabledTrue(messageCode, lang);
        if(message == null) {
            throw new DataNotFoundException();
        }
        MessageOutput outParam = new MessageOutput(message.getMessageCode(), message.getText());
        return outParam;
    }
}
