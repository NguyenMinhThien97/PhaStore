package com.store.pharmacy.common.service;

import com.store.pharmacy.common.model.LabelInput;

import java.util.HashMap;
import java.util.List;

public interface LabelService {
    List<HashMap> findByLabelCodes(List<LabelInput> labelInputs, String lang);

    String findByExactLabelCodes(String labelcode,String lang);
}
