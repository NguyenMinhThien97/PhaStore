package com.store.pharmacy.common.service.impl;

import com.store.pharmacy.common.model.Label;
import com.store.pharmacy.common.model.LabelInput;
import com.store.pharmacy.common.repository.LabelRepository;
import com.store.pharmacy.common.service.LabelService;
import com.store.pharmacy.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    LabelRepository labelRepository;

    @Override
    public List<HashMap> findByLabelCodes(List<LabelInput> labelInputs, String lang){
        List<HashMap> outputParams = new ArrayList<>();
        if(labelInputs.isEmpty()){
            throw new DataNotFoundException();
        }
        for (LabelInput input: labelInputs) {
            HashMap labelOutput = new HashMap();
            Label label = labelRepository.findByLabelCodeAndLangAndEnabledTrue(input.getCode(), lang);
            if(label != null) {
                labelOutput.put(label.getLabelCode(), label.getName());
            }else {
                labelOutput.put(input.getCode(), null);
            }
            outputParams.add(labelOutput);
        }
        return outputParams;
    }
}
