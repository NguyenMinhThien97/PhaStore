package com.store.pharmacy.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.store.pharmacy.model.CategoryDTO;
import com.store.pharmacy.utils.ExecContext;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CategoryValidator implements Validator {

	@Autowired
	private ExecContext execContext;

	@Override
	public boolean supports(Class<?> clazz) {
		return CategoryDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CategoryDTO categoryDTO = (CategoryDTO) target;
		/**
		 * hardcode true for isPost and false for isPatch when testing post.
		 * hardcode true for isPatch and false for isPost when testing patch.
		 */
		boolean isPost = execContext.isPostRequest();
//		boolean isPost = true;
		boolean isPatch = execContext.isPatchRequest();
//		boolean isPatch = true;
		if (isPost || isPatch && categoryDTO.getCategoryName() != null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryName", "MSG0014");
		}
		if (isPost) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryId", "MSG0012");
		}
	}
}
