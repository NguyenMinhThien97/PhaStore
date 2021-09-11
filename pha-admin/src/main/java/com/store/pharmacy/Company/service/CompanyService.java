package com.store.pharmacy.Company.service;

import com.store.pharmacy.Company.model.CompanyInput;
import com.store.pharmacy.Company.model.CompanyOutput;

public interface CompanyService {
    CompanyOutput save(CompanyInput createForm);
    CompanyOutput update(String idCompany, CompanyInput companyInput);
}
