package com.store.pharmacy.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyOutput {

    private long idCompany;

    private String name;

    private String taxCode;

    private String description;

    private String userId;

    private LocalDateTime updatedAt;
}
