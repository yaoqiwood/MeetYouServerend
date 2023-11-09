package com.cx.meetyoubackend;

import com.cx.meetyoubackend.utils.BcryptUtil;

public class UtilsTest {

	public static void main(String[] args) {
		String rawPassword = "123456";
		String encodedPassword = BcryptUtil.encode(rawPassword);

		System.out.println("Encoded Password: " + encodedPassword);

		boolean isMatch = BcryptUtil.matches(rawPassword,
			"$2a$10$vRTL3IzX6CThlM0SThvIHeg5rBLZ8dlLZ1NesYPPJO.W9ZMOzPCSS");
		System.out.println("Password matches: " + isMatch);
	}


}
