package com.cx.meetyoubackend.constans;

import lombok.Getter;

@Getter
public enum ResponseBodyEnum {
	TOKEN("token"),
	MESSAGE("message"),
	STATUS("status"),
	SUCCESS("success"),
	CAPTCHA_REQUIRED("is_captcha_required");

	ResponseBodyEnum(String value) {
		this.value = value;
	}

	private final String value;

}
