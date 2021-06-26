package com.store.pharmacy.common.repository;

import com.store.pharmacy.common.model.Common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonRepository extends JpaRepository<Common, Long> {

    List<Common> findByCommonCodeAndEnabledTrue(String commonCode);
}
