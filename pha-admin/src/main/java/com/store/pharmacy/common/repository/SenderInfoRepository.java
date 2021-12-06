package com.store.pharmacy.common.repository;

import com.store.pharmacy.common.model.SenderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SenderInfoRepository extends JpaRepository<SenderInfo, Long> {
    List<SenderInfo> findBySenderTypeAndEnabledTrue(String SenderType);
}
