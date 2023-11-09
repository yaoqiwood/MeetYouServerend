package com.cx.meetyoubackend.constans;

import lombok.Getter;

@Getter
public enum ResponseBodyEnum {
	TOKEN("token"),
	MESSAGE("message"),
	STATUS("status"),
	SUCCESS("success");

	ResponseBodyEnum(String value) {
		this.value = value;
	}

	private final String value;

}
