package com.store.pharmacy.Company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInput {

    @NotBlank(message = "MSG0020")
    private String name;

    @NotBlank(message = "MSG0020")
    @Size(min = 3, message = "MSG0021.3")
    private String taxCode;

    private String description;

    @NotBlank(message = "MSG0020")
    private String userId;

}
