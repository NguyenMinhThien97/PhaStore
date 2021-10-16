package com.store.pharmacy.Company.serviceImpl;

import com.store.pharmacy.Company.model.Company;
import com.store.pharmacy.Company.model.CompanyInput;
import com.store.pharmacy.Company.model.CompanyOutput;
import com.store.pharmacy.Company.repository.CompanyRepository;
import com.store.pharmacy.Company.service.CompanyService;
import com.store.pharmacy.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    private final String INSERT = "INSERT";
    private final String UPDATE = "UPDATE";

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

    private Company convertCompanyInputToCompany(Company companyOutput, CompanyInput inParam, String action) {
        LocalDateTime today = LocalDateTime.now();
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
        if (action.equals(INSERT)) {
            outParam.setUserId(company.getCreatedBy());
            outParam.setUpdatedAt(company.getCreatedAt().toLocalDate());
        } else if (action.equals(UPDATE)) {
            outParam.setUserId(company.getUpdatedBy());
            outParam.setUpdatedAt(company.getUpdatedAt().toLocalDate());
        }
        return outParam;
    }
}
