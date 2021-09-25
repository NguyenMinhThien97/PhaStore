package com.store.pharmacy.bizpartner.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BizPartnerDTO {
	
	private String bizPartnerName;
	private String taxCode;
	private String bizTypeCode;
	private String country;
	private String address;
	private String email;
	private String phoneNumber;
	private String description;
	private String status;
	private Boolean enabled;
}
