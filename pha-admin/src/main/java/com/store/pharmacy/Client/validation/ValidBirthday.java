package com.store.pharmacy.Client.validation;

import com.store.pharmacy.utils.Formatter;
import org.springframework.beans.factory.annotation.Configurable;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthdayValidator.class)
public @interface ValidBirthday {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

@Configurable
class BirthdayValidator implements ConstraintValidator<ValidBirthday, String> {

    @Override
    public boolean isValid(String birthday, ConstraintValidatorContext context) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        if (birthday != null) {
            try {
                LocalDate birthdayFormat = LocalDate.parse(birthday, dtf);
                if (!Formatter.isValidDateOfBirth(birthdayFormat)) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}