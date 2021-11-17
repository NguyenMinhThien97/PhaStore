package com.store.pharmacy.bizpartner.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BIZ_PARTNER_ID", length = 20, nullable = false)
	private long bizPartnerId;

	@Column(name = "NAME", length = 255, nullable = false)
	private String bizPartnerName;

	@Column(name = "TAX_CODE", length = 50, nullable = false)
	private String taxCode;

	@Column(name = "BIZ_TYPE_CODE", length = 255, nullable = false)
	private String bizTypeCode;

	@Column(name = "COUNTRY", length = 255, nullable = false)
	private String country;

	@Column(name = "ADDRESS", length = 255, nullable = false)
	private String address;

	@Column(name = "EMAIL", length = 255, nullable = false)
	private String email;

	@Column(name = "PHONENUMBER", length = 255, nullable = false)
	private String phoneNumber;

	@Column(name = "DESCRIPTION", length = 255)
	private String description;

	@Column(name = "STATUS", length = 255, nullable = false)
	private String status;

	@Column(name = "Enabled", length = 1, nullable = false)
	private Boolean enabled;

	@Column(name = "CREATED_BY", length = 255, nullable = false)
	private String createdBy;

	@Column(name = "CREATED_AT", nullable = false)
	private LocalDate createdAt;

	@Column(name = "UPDATED_BY", length = 255, nullable = false)
	private String updatedBy;

	@Column(name = "UPDATED_AT", nullable = false)
	private LocalDate updatedAt;

}
