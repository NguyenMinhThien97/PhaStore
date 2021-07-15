package com.store.pharmacy.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.store.pharmacy.securities.model.UserDTO;
import com.store.pharmacy.utils.ExecContext;
import com.store.pharmacy.utils.Formatter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserValidator implements Validator {

	@Autowired
	private ExecContext execContext;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDTO userDTO = (UserDTO) target;
		/**
		 * hardcode true for isPost and false for isPatch when testing post.
		 * hardcode true for isPatch and false for isPost when testing patch.
		 */
		boolean isPost = execContext.isPostRequest();
//		boolean isPost = true;
		boolean isPatch = execContext.isPatchRequest();
//		boolean isPatch = true;

		if (userDTO.getEmail() != null && !Formatter.isValidEmailAddress(userDTO.getEmail())) {
			errors.rejectValue("email", "MSG0009");
		}

		if (isPost || isPatch && userDTO.getFirstName() != null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "MSG0005");
		}

		if (isPost || isPatch && userDTO.getLastName() != null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "MSG0006");
		}

		if (isPost || isPatch && userDTO.getRoleName() != null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "MSG0017");
		}

		if (isPost || isPatch && userDTO.getDateOfBirth() != null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "MSG0018");
			if (errors != null && errors.getFieldError("dateOfBirth") == null
					&& !Formatter.isValidDateOfBirth(userDTO.getDateOfBirth())) {
				errors.rejectValue("dateOfBirth", "MSG0007");
			}
		}

		if (isPost || isPatch && userDTO.getPhoneNumber() != null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "MSG0004");
			if (errors != null && errors.getFieldError("phoneNumber") == null
					&& !Formatter.isValidPhoneNumber(userDTO.getPhoneNumber())) {
				errors.rejectValue("phoneNumber", "MSG0003");
			}
		}
	}
}
