package com.store.pharmacy.bizpartner.service;


import com.store.pharmacy.bizpartner.model.BizPartnerOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BizPartnerService {

    List<BizPartnerOutput> findBizPartnerByName(String bizPartnerName, String bizTypeCode, boolean enabled, String sortBy);
}
