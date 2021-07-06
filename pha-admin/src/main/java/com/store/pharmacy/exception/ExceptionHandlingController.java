package com.store.pharmacy.exception;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.context.MessageSource;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({ DataNotFoundException.class })
	public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex) {
		String localizedErrorMessage = resolveLocalizedErrorMessage(ex.getMessage(), ex.getArgs());
		FieldErrorDTO fieldErrorDTO = new FieldErrorDTO(null, localizedErrorMessage);
		return new ResponseEntity<Object>(fieldErrorDTO, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ DuplicateDataException.class })
	public ResponseEntity<Object> handleDuplicateDataException(DuplicateDataException ex) {
		String localizedErrorMessage = resolveLocalizedErrorMessage(ex.getMessage(), ex.getArgs());
		FieldErrorDTO fieldErrorDTO = new FieldErrorDTO(null, localizedErrorMessage);
		return new ResponseEntity<Object>(fieldErrorDTO, HttpStatus.CONFLICT);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		ValidationErrorDTO dto = new ValidationErrorDTO();
		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError.getCode(),
					fieldError.getArguments());
			dto.addFieldError(fieldError.getField(), localizedErrorMessage);
		}
		return new ResponseEntity<Object>(dto, headers, status);
	}

	private String resolveLocalizedErrorMessage(String fieldErrorCode, Object[] args) {
		return messageSource.getMessage(fieldErrorCode, args, new Locale("EN"));
	}
}
