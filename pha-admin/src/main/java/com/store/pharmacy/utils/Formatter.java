package com.store.pharmacy.utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatter {

	public static final String PHONE_REGEX_1 = "\\d{10}";
	public static final String PHONE_REGEX_2 = "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}";
	public static final String PHONE_REGEX_3 = "\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}";
	public static final String PHONE_REGEX_4 = "\\(\\d{3}\\)-\\d{3}-\\d{4}";
	public static final String PHONE_REGEX_5 = "\\(\\d{3}\\)\\d{3}-\\d{4}";

	public static boolean isValidPhoneNumber(String phoneNbr) {
		// validate phone numbers of format "1234567890"
		if (phoneNbr.matches(PHONE_REGEX_1)) {
			return true;
		}
		// validate phone number with -, . or spaces
		if (phoneNbr.matches(PHONE_REGEX_2)) {
			return true;
		}
		// validate phone number with extension length from 3 to 5
		if (phoneNbr.matches(PHONE_REGEX_3)) {
			return true;
		}
		// validate phone number where area code is in braces ()
		if (phoneNbr.matches(PHONE_REGEX_4)) {
			return true;
		}
		// validate phone number where area code is in braces ()
		if (phoneNbr.matches(PHONE_REGEX_5)) {
			return true;
		}
		// return false if nothing matches the input
		return false;
	}

	public static boolean isValidDateOfBirth(LocalDate dateOfBirth) {
		if (dateOfBirth.isEqual(LocalDate.now()) || dateOfBirth.isAfter(LocalDate.now())) {
			return false;
		}
		return true;
	}

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
