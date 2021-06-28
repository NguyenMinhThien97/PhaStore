package com.store.pharmacy.common.serviceImpl;

import com.store.pharmacy.common.model.Label;
import com.store.pharmacy.common.model.LabelInput;
import com.store.pharmacy.common.repository.LabelRepository;
import com.store.pharmacy.common.service.LabelService;
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
    public List<HashMap> findByLabelCodes(List<LabelInput> labelInputs){
        List<HashMap> outputParams = new ArrayList<>();
        for (LabelInput input: labelInputs) {
            Label label = labelRepository.findByLabelCodeAndEnabledTrue(input.getCode());
            if(label != null) {
                HashMap labelOutput = new HashMap();
                labelOutput.put(label.getLabelCode(), label.getName());
                outputParams.add(labelOutput);
            }
        }
        return outputParams;
    }
}
