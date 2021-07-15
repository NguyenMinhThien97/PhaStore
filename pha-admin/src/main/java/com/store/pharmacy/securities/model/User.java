package com.store.pharmacy.securities.model;

import java.io.Serializable;
import java.time.LocalDate;
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
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UserId", length = 11, nullable = false)
	private String userId;

	@Column(name = "RoleCode", length = 3, nullable = false)
	private int roleCode;

	@Column(name = "FirstName", length = 255, nullable = false)
	private String firstName;

	@Column(name = "LastName", length = 255, nullable = false)
	private String lastName;

	@Column(name = "UserName", length = 255, nullable = false)
	private String userName;

	@Column(name = "Email", length = 255)
	private String email;

	@Column(name = "DateOfBirth", nullable = false)
	private LocalDate dateOfBirth;

	@Column(name = "PhoneNumber", length = 255, nullable = false)
	private String phoneNumber;

	@Column(name = "Address", length = 255)
	private String address;

	@Column(name = "Password", length = 255, nullable = false)
	private String password;

	@Column(name = "Enabled", nullable = false)
	private boolean enabled;

	@Column(name = "CreatedBy", length = 255)
	private String createBy;

	@Column(name = "CreatedAt", nullable = false)
	private LocalDateTime createAt;

	@Column(name = "UpdatedBy")
	private String updatedBy;

	@Column(name = "UpdatedAt")
	private LocalDateTime updatedAt;
}
