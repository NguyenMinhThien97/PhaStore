package com.store.pharmacy.bizpartner.repository;

import com.store.pharmacy.bizpartner.model.BizPartner;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BizPartnerRepository extends JpaRepository<BizPartner, String> {

    List<BizPartner> findAllByBizPartnerNameAndBizTypeCodeAndEnabled(String bizPartnerName, String bizTypeCode, boolean enabled, Sort sort);

}
