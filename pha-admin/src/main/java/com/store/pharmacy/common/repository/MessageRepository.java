package com.store.pharmacy.common.repository;

import com.store.pharmacy.common.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

    Message findByMessageCodeAndEnabledTrue(String messageCode);
}