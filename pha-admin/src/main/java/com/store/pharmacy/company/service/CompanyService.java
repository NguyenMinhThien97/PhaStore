package com.store.pharmacy.company.service;

import com.store.pharmacy.company.model.Company;
import com.store.pharmacy.company.model.CompanyInput;
import com.store.pharmacy.company.model.CompanyOutput;
import com.store.pharmacy.company.model.CompanySearchList;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

public interface CompanyService {
    CompanyOutput save(CompanyInput createForm);
    CompanyOutput update(String idCompany, CompanyInput companyInput);
    CompanySearchList getCompanies(Specification<Company> spec, Sort sort, HttpHeaders headers);
}
