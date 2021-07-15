package com.store.pharmacy.securities.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO extends RepresentationModel<UserDTO> {

	private String roleName;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dateOfBirth;
	private String phoneNumber;
	private String address;
	private Boolean enabled;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String currentPassword;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String newPassword;
}
