package com.store.pharmacy.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object[] args = new Object[] {};

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(String message, Object[] args) {
		super(message);
		this.args = args;
	}
}
