package com.store.pharmacy.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FieldErrorDTO {

	private String field;
	private String message;

	public FieldErrorDTO(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}
}
