package com.cx.meetyoubackend.constans;

public enum CaptchaConstants {

	TEMP_TOKEN_KEY("temp_token_key"),
	CAPTCHA_CODE_PREFIX("CAPTCHA_CODE_"),
	LOGIN_FAIL_PREFIX("LOGIN_FAIL_"),
	CAPTCHA_EXPIRATION_MINUTES(5),

	LOGIN_SUCCESS_PREFIX("LOGIN_SUCCESS_");

	private final String value;
	private final int intValue;

	// Constructor for string constants
	CaptchaConstants(String value) {
		this.value = value;
		this.intValue = -1; // Default value for int, indicating not used
	}

	// Constructor for int constants
	CaptchaConstants(int intValue) {
		this.intValue = intValue;
		this.value = null; // Default value for String, indicating not used
	}

	public String getValue() {
		return value;
	}

	public int getIntValue() {
		return intValue;
	}
}

