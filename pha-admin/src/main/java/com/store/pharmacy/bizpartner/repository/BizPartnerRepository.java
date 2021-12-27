package com.store.pharmacy.bizpartner.repository;

import com.store.pharmacy.bizpartner.model.BizPartner;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BizPartnerRepository extends JpaRepository<BizPartner, String> {

    @Query("SELECT c FROM BizPartner c WHERE (:bizPartnerName is null or c.bizPartnerName = :bizPartnerName) "
            +" and (:bizTypeCode is null or c.bizTypeCode = :bizTypeCode)"
            +" and (:enabled is null or c.enabled = :enabled)")
    List<BizPartner> findAllByBizPartnerNameAndBizTypeCodeAndEnabled(String bizPartnerName, String bizTypeCode, boolean enabled, Sort sort);

}
