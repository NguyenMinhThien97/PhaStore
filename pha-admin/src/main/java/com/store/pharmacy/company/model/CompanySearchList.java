package com.store.pharmacy.company.model;

import com.store.pharmacy.utils.Paging;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CompanySearchList {

    private Paging page;

    private List<CompanyOutput> companies;
}
