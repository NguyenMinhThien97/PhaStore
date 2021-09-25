package com.store.pharmacy.bizpartner.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BizPartner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BIZ_PARTNER_ID", length = 20, nullable = false)
	private String bizPartnerId;

	@Column(name = "BIZ_PARTNER_NAME", length = 255, nullable = false)
	private String bizPartnerName;

	@Column(name = "TAX_CODE", length = 255, nullable = false)
	private String taxCode;

	@Column(name = "BIZ_TYPE_CODE", length = 255, nullable = false)
	private String bizTypeCode;

	@Column(name = "COUNTRY", length = 255, nullable = false)
	private String country;

	@Column(name = "ADDRESS", length = 255)
	private String address;

	@Column(name = "EMAIL", length = 255)
	private String email;

	@Column(name = "PHONE_NUMBER", length = 255, nullable = false)
	private String phoneNumber;

	@Column(name = "DESCRIPTION", length = 255)
	private String description;

	@Column(name = "STATUS", length = 255, nullable = false)
	private String status;

	@Column(name = "Enabled", length = 1, nullable = false)
	private boolean enabled;

	@Column(name = "CREATED_BY", length = 255)
	private String createBy;

	@Column(name = "CREATED_AT")
	private LocalDateTime createAt;

	@Column(name = "UPDATED_BY", length = 255)
	private String updateBy;

	@Column(name = "UPDATED_AT")
	private LocalDateTime updateAt;
}
