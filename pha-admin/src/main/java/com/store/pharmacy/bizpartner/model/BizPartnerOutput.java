package com.store.pharmacy.bizpartner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BizPartnerOutput  {

    private long bizPartnerId;

    private String bizPartnerName;

    private String taxCode;

    private String bizTypeCode;

    private String country;

    private String address;

    private String email;

    private String phoneNumber;

    private String description;

    private Boolean enabled;

    private String updateBy;

    private LocalDate updateAt;
}
