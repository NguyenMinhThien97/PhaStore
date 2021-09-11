package com.store.pharmacy.common.repository;

import com.store.pharmacy.common.model.Common;
import com.store.pharmacy.common.model.CommonId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonRepository extends JpaRepository<Common, CommonId> {

	List<Common> findByCommonCodeAndLangAndEnabledTrue(String commonCode, String lang);

	@Query("SELECT c FROM Common c WHERE c.commonCode = :commonCode AND name = :name")
	Common findByCommonCodeAndName(@Param("commonCode") String commonCode, @Param("name") String name);

	@Query("SELECT c FROM Common c WHERE c.commonCode = :commonCode AND c.sequenceNo = :sequenceNo")
	Common findByCommonCodeAndSequenceNo(@Param("commonCode") String commonCode, @Param("sequenceNo") int sequenceNo);
}
