package com.store.pharmacy.company.controller;

import com.store.pharmacy.company.model.Company;
import com.store.pharmacy.company.model.CompanyInput;
import com.store.pharmacy.company.model.CompanyOutput;
import com.store.pharmacy.company.model.CompanySearchList;
import com.store.pharmacy.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public HttpEntity<CompanyOutput> createCompany(@Valid @RequestBody CompanyInput companyInput) {
        CompanyOutput outParam = companyService.save(companyInput);
        return new ResponseEntity<>(outParam, HttpStatus.CREATED);
    }

    @PutMapping("/{idCompany}")
    public HttpEntity<CompanyOutput> updateCompany(@PathVariable("idCompany") String idCompany, @Valid @RequestBody CompanyInput companyInput) {
        CompanyOutput outParam = companyService.update(idCompany, companyInput);
        return new ResponseEntity<>(outParam, HttpStatus.OK);
    }

    @GetMapping
    public HttpEntity<CompanySearchList> getCompanies(@And({
            @Spec(path = "name", params = "name", spec = Like.class),
            @Spec(path = "taxCode", params = "taxCode", spec = Like.class),
    }) Specification<Company> spec, Sort sort, @RequestHeader HttpHeaders headers) {
        CompanySearchList outputParam = companyService.getCompanies(spec, sort, headers);
        return new ResponseEntity<>(outputParam, HttpStatus.OK);
    }
}
