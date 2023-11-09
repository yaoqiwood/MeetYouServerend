package com.cx.meetyoubackend.constans;

import lombok.Getter;

@Getter
public enum StatusEnum {

	SUCCESS("success"), WARNING("warning"), ERROR("error");

	private final String value;

	StatusEnum(String value) {
		this.value = value;
	}
}
