package com.store.pharmacy.company.service;

import com.store.pharmacy.company.model.CompanyInput;
import com.store.pharmacy.company.model.CompanyOutput;

public interface CompanyService {
    CompanyOutput save(CompanyInput createForm);
    CompanyOutput update(String idCompany, CompanyInput companyInput);
}
