package com.store.pharmacy.company.controller;

import com.store.pharmacy.company.model.CompanyInput;
import com.store.pharmacy.company.model.CompanyOutput;
import com.store.pharmacy.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
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
}
