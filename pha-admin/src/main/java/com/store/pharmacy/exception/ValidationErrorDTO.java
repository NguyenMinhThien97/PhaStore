package com.store.pharmacy.exception;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValidationErrorDTO {
	
    @JsonProperty("errors")
	private List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

	public void addFieldError(String field, String message) {
		FieldErrorDTO fieldErrorDTO = new FieldErrorDTO(field, message);
		fieldErrors.add(fieldErrorDTO);
	}
}
