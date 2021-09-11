package com.store.pharmacy.exception;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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

import com.store.pharmacy.common.model.Message;
import com.store.pharmacy.common.repository.MessageRepository;

import org.springframework.context.NoSuchMessageException;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageRepository messageRepository;

	private static final String DEFAULT_LOCALE_CODE = "en";

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
			String localizedErrorMessage;
			if(fieldError.getDefaultMessage() == null) {
				localizedErrorMessage = resolveLocalizedErrorMessage(fieldError.getCode(),
						fieldError.getArguments());
			}else {
				localizedErrorMessage = resolveLocalizedErrorMessage(fieldError.getField(), fieldError.getDefaultMessage());
			}
			dto.addFieldError(fieldError.getField(), localizedErrorMessage);
		}
		return new ResponseEntity<Object>(dto, headers, status);
	}

	private String resolveLocalizedErrorMessage(String fieldErrorCode, Object[] args) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		Message message = messageRepository.findByMessageCodeAndLangAndEnabledTrue(fieldErrorCode, DEFAULT_LOCALE_CODE);
		if (message == null) {
			message = messageRepository.findByMessageCodeAndLangAndEnabledTrue(fieldErrorCode, DEFAULT_LOCALE_CODE);
			if (message == null) {
				throw new NoSuchMessageException(fieldErrorCode);
			}
		}
		return MessageFormat.format(message.getText().trim(), args);
	}

	private String resolveLocalizedErrorMessage(String fieldErrorCode, String messageCodes ) {
		String messageCode;
		int stringNumber = StringUtils.countMatches(messageCodes, ".");
		String[] filedNames = new String[stringNumber+ 1];
		filedNames[0]= fieldErrorCode;
		if(stringNumber > 0) {
			messageCode = messageCodes.substring(0, messageCodes.indexOf("."));
			messageCodes = messageCodes.replaceAll(messageCode + ".", "");
			stringNumber--;
			int i = 0;
			int j = 0;
			while ( i <= stringNumber) {
				String filedName = null;
				if(stringNumber == 0) {
					filedName = messageCodes.substring( 0, messageCodes.length());
				}else {
					filedName = messageCodes.substring( 0, messageCodes.indexOf("."));
				}
				filedNames[++j]= filedName;
				messageCodes = messageCodes.replaceAll(filedName + ".", "");
				stringNumber--;
			}
		}else {
			messageCode = messageCodes;
		}

		Message message = messageRepository.findByMessageCodeAndLangAndEnabledTrue(messageCode, DEFAULT_LOCALE_CODE);
		if (message == null) {
			message = messageRepository.findByMessageCodeAndLangAndEnabledTrue(messageCode, DEFAULT_LOCALE_CODE);
		}
		return MessageFormat.format(message.getText().trim(), filedNames);
	}
}
