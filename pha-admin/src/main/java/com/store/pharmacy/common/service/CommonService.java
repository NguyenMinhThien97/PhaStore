package com.store.pharmacy.common.service;

import com.store.pharmacy.common.model.Common;
import com.store.pharmacy.common.model.CommonOutput;

import java.util.List;

public interface CommonService {
    List<CommonOutput> findByCommonCode(String commonCode, String lang);
}
