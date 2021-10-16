package com.store.pharmacy.Company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInput {

    @NotBlank(message = "MSG0020")
    @Size(max = 255, message = "MSG0022.255")
    private String name;

    @NotBlank(message = "MSG0020")
    @Size(min = 3,max = 50, message = "MSG0021.3.50")
    private String taxCode;

    @Size(max = 255, message = "MSG0022.255")
    private String description;

    @NotBlank(message = "MSG0020")
    private String userId;

}
