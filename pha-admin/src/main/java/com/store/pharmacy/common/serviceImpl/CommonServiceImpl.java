package com.store.pharmacy.common.serviceImpl;

import com.store.pharmacy.common.model.Common;
import com.store.pharmacy.common.model.CommonOutput;
import com.store.pharmacy.common.repository.CommonRepository;
import com.store.pharmacy.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonRepository commonRepository;

    @Override
    public List<CommonOutput> findByCommonCode(String commonCode, String lang) {
        List<CommonOutput> outputParams = new ArrayList<>();

        List<Common> commons = commonRepository.findByCommonCodeAndLangAndEnabledTrue(commonCode, lang);
        for (Common c: commons) {
            CommonOutput commonOutput = new CommonOutput(c.getCommonCode(), c.getName());
            outputParams.add(commonOutput);
        }
        return outputParams;
    }
}
