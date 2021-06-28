package com.store.pharmacy.common.serviceImpl;

import com.store.pharmacy.common.model.Message;
import com.store.pharmacy.common.model.MessageOutput;
import com.store.pharmacy.common.repository.MessageRepository;
import com.store.pharmacy.common.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public MessageOutput getByMessageCode(String messageCode) {
        Message message = messageRepository.findByMessageCodeAndEnabledTrue(messageCode);
        if(message != null) {
            MessageOutput outParam = new MessageOutput(message.getMessageCode(), message.getText());
            return outParam;
        }
        return null;
    }
}
