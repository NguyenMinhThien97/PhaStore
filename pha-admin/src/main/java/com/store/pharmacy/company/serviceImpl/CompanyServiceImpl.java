package com.store.pharmacy.company.serviceImpl;

import com.store.pharmacy.company.model.Company;
import com.store.pharmacy.company.model.CompanyInput;
import com.store.pharmacy.company.model.CompanyOutput;
import com.store.pharmacy.company.model.CompanySearchList;
import com.store.pharmacy.company.repository.CompanyRepository;
import com.store.pharmacy.company.service.CompanyService;
import com.store.pharmacy.exception.DataNotFoundException;
import com.store.pharmacy.utils.Paging;
import com.store.pharmacy.utils.PharmacyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    private final String INSERT = "INSERT";
    private final String UPDATE = "UPDATE";
    private final String FETCH = "FETCH";

    @Override
    public CompanyOutput save(CompanyInput companyInput) {
        Company inParam = convertCompanyInputToCompany(null, companyInput, INSERT);
        Company savedCompany = companyRepository.save(inParam);
        CompanyOutput outParam = convertCompanyToCompanyOutput(savedCompany, INSERT);
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
        Company inParam = convertCompanyInputToCompany(company, companyInput, UPDATE);
        Company savedCompany = companyRepository.save(inParam);
        CompanyOutput outParam = convertCompanyToCompanyOutput(savedCompany, UPDATE);
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
            companies.forEach(c -> companiesOutput.add(convertCompanyToCompanyOutput(c, FETCH)));
            return new CompanySearchList(new Paging(companyPage.getTotalElements(), (long) companyPage.getNumber(), (long) companyPage.getNumberOfElements(), pageable.getOffset(), (long) companyPage.getTotalPages()), companiesOutput);
        } else {
            List<Company> companies = companyRepository.findAll(spec, sort);
            companies.forEach(c -> companiesOutput.add(convertCompanyToCompanyOutput(c, FETCH)));
            return new CompanySearchList(new Paging((long) companiesOutput.size(), 0L, 0L, 0L, 0L), companiesOutput);
        }
    }

    private Company convertCompanyInputToCompany(Company companyOutput, CompanyInput inParam, String action) {
        LocalDate today = LocalDate.now();
        Company company = new Company();
        if (action.equals(INSERT)) {
            company.setName(inParam.getName());
            company.setTaxCode(inParam.getTaxCode());
            company.setDescription(inParam.getDescription());
            company.setCreatedBy(inParam.getUserId());
            company.setCreatedAt(today);
        } else if (action.equals(UPDATE)) {
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
        if (action.equals(INSERT) || action.equals(FETCH)) {
            outParam.setUserId(company.getCreatedBy());
            outParam.setUpdatedAt(company.getCreatedAt());
        } else if (action.equals(UPDATE)) {
            outParam.setUserId(company.getUpdatedBy());
            outParam.setUpdatedAt(company.getUpdatedAt());
        }
        return outParam;
    }
}
