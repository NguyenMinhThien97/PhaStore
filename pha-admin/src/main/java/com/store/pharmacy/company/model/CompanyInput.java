package com.store.pharmacy.company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInput {

    @NotBlank(message = "MSG0020")
    private String name;

    @NotBlank(message = "MSG0020")
    @Min(value = 3, message = "MSG0021.3")
    private String taxCode;

    private String description;

    @NotBlank(message = "MSG0020")
    private String userId;

}
