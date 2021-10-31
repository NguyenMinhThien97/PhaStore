package com.store.pharmacy.company.service.impl;

import com.store.pharmacy.company.model.Company;
import com.store.pharmacy.company.model.CompanyInput;
import com.store.pharmacy.company.model.CompanyOutput;
import com.store.pharmacy.company.model.CompanySearchList;
import com.store.pharmacy.company.repository.CompanyRepository;
import com.store.pharmacy.company.service.CompanyService;
import com.store.pharmacy.exception.DataNotFoundException;
import com.store.pharmacy.utils.Paging;
import com.store.pharmacy.utils.PharmacyConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyOutput save(CompanyInput companyInput) {
        Company inParam = convertCompanyInputToCompany(new Company(), companyInput, PharmacyConstant.INSERT);
        Company savedCompany = companyRepository.save(inParam);
        CompanyOutput outParam = convertCompanyToCompanyOutput(savedCompany, PharmacyConstant.INSERT);
        return outParam;
    }

    @Override
    public CompanyOutput update(String idCompany, CompanyInput companyInput) {
        long idInput;
        try {
            idInput = Long.parseLong(idCompany);
        } catch (NumberFormatException ex) {
            throw new DataNotFoundException();
        }
        Company company = companyRepository.findById(idInput).get();
        if (company == null) {
            throw new DataNotFoundException();
        }
        Company inParam = convertCompanyInputToCompany(company, companyInput, PharmacyConstant.UPDATE);
        Company savedCompany = companyRepository.save(inParam);
        CompanyOutput outParam = convertCompanyToCompanyOutput(savedCompany, PharmacyConstant.UPDATE);
        return outParam;
    }

    @Override
    public CompanySearchList getCompanies(Specification<Company> spec, Sort sort, HttpHeaders headers) {
        List<CompanyOutput> companiesOutput = new ArrayList<>();
        boolean isPageable = headers.containsKey(PharmacyConstant.PAGE_NUMBER) && headers.containsKey(PharmacyConstant.PAGE_SIZE);
        if (isPageable) {
            int size = Integer.parseInt(Objects.requireNonNull(headers.get(PharmacyConstant.PAGE_SIZE)).get(0));
            int page = Integer.parseInt(Objects.requireNonNull(headers.get(PharmacyConstant.PAGE_NUMBER)).get(0));
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<Company> companyPage = companyRepository.findAll(spec, pageable);
            List<Company> companies = companyPage.getContent();
            companies.forEach(c -> companiesOutput.add(convertCompanyToCompanyOutput(c, PharmacyConstant.FETCH)));
            return new CompanySearchList(new Paging(companyPage.getTotalElements(), (long) companyPage.getNumber(), (long) companyPage.getNumberOfElements(), pageable.getOffset(), (long) companyPage.getTotalPages()), companiesOutput);
        } else {
            List<Company> companies = companyRepository.findAll(spec, sort);
            companies.forEach(c -> companiesOutput.add(convertCompanyToCompanyOutput(c, PharmacyConstant.FETCH)));
            return new CompanySearchList(new Paging((long) companiesOutput.size(), 0L, 0L, 0L, 0L), companiesOutput);
        }
    }

    private Company convertCompanyInputToCompany(Company companyOutput, CompanyInput inParam, String action) {
        LocalDateTime today = LocalDateTime.now();
        Company company = new Company();
        if (action.equals(PharmacyConstant.INSERT)) {
            company.setName(inParam.getName());
            company.setTaxCode(inParam.getTaxCode());
            company.setDescription(inParam.getDescription());
            company.setCreatedBy(inParam.getUserId());
            company.setCreatedAt(today);
        } else if (action.equals(PharmacyConstant.UPDATE)) {
            company.setIdCompany(companyOutput.getIdCompany());
            if (!inParam.getName().isBlank() || !inParam.getName().isEmpty()) {
                company.setName(companyOutput.getName());
            }
            if (!inParam.getTaxCode().isBlank() || !inParam.getTaxCode().isEmpty()) {
                company.setTaxCode(companyOutput.getTaxCode());
            }
            if (!inParam.getDescription().isBlank() || !inParam.getDescription().isEmpty()) {
                company.setDescription(companyOutput.getDescription());
            }
            company.setUpdatedBy(inParam.getUserId());
            company.setUpdatedAt(today);
        }
        return company;
    }

    private CompanyOutput convertCompanyToCompanyOutput(Company company, String action) {
        CompanyOutput outParam = new CompanyOutput();
        outParam.setIdCompany(company.getIdCompany());
        outParam.setName(company.getName());
        outParam.setTaxCode(company.getTaxCode());
        outParam.setDescription(company.getDescription());
        if (action.equals(PharmacyConstant.INSERT)) {
            outParam.setUserId(company.getCreatedBy());
            outParam.setUpdatedAt(company.getCreatedAt());
        } else if (action.equals(PharmacyConstant.UPDATE)) {
            outParam.setUserId(company.getUpdatedBy());
            outParam.setUpdatedAt(company.getUpdatedAt());
        }
        return outParam;
    }
}
