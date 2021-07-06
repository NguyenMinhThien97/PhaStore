package com.store.pharmacy.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DuplicateDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object[] args = new Object[] {};

	public DuplicateDataException(String message) {
		super(message);
	}

	public DuplicateDataException(String message, Object[] args) {

		super(message);
		this.args = args;
	}
}
