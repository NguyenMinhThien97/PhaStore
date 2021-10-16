package com.store.pharmacy.Company.controller;

import com.store.pharmacy.Company.model.CompanyInput;
import com.store.pharmacy.Company.model.CompanyOutput;
import com.store.pharmacy.Company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

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
}
