package com.myProject.car_rent.helpermodule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

	//constructor
	private InputValidator() {

	}
	//validate email
	public static boolean isValidEmail(String email) {
		String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);// CASE_INSENSITIVE matching
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	//validate password
	public static boolean isValidPassword(String password) {
		// Check length
		if (password.length() < 8) {
			return false;
		}

		// Check first character
		char firstChar = password.charAt(0);
		if (Character.isDigit(firstChar) || isSpecialCharacter(firstChar)) {
			return false;
		}

		// Check for at least one lower case letter, one upper case letter, one digit,
		// and one special character
		boolean hasLowercase = false;
		boolean hasUppercase = false;
		boolean hasDigit = false;
		boolean hasSpecial = false;
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isLowerCase(c)) {
				hasLowercase = true;
			}

			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			}

			if (Character.isDigit(c)) {
				hasDigit = true;
			}

			if (isSpecialCharacter(c)) {
				hasSpecial = true;
			}
		}

		return hasLowercase && hasUppercase && hasDigit && hasSpecial;
	}

	private static boolean isSpecialCharacter(char c) {
		String specialChars = "!@#$%^&*()_+{}:\"<>?|[];',./`~\\-=";
		return specialChars.indexOf(c) != -1;
	}
	
	//password
	// * atleast one special char,one uppercase,one lowercase
	// * min length 8
	// * starting letter is alphabet only
	
	public static boolean isValidPhoneNumber(String phn_no)
	{
		String regex="^[0-9]{10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phn_no);
		return matcher.matches();
	}
	
}
