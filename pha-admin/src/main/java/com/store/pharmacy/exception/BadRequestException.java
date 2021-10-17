package com.store.pharmacy.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BadRequestException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Object[] args = new Object[] {};

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Object[] args) {
        super(message);
        this.args = args;
    }
}