package com.store.pharmacy.company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyOutput {

    private long idCompany;

    private String name;

    private String taxCode;

    private String description;

    private String userId;

    private LocalDate updatedAt;
}
