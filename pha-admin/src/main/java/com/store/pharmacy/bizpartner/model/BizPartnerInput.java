package com.store.pharmacy.bizpartner.model;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BizPartnerInput {

    @NotBlank(message = "MSG0020")
    private String bizPartnerName;

    private String taxCode;

    @NotBlank(message = "MSG0020" )
    private String bizTypeCode ;

    private String country;

    private String address;

    private String email;

    private String phoneNumber;

    private String description;

    @NotBlank(message = "MSG0020")
    private Boolean enabled;

    private String userId;

    private String status;

}

