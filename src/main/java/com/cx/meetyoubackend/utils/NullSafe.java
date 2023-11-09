package com.cx.meetyoubackend.utils;

public class NullSafe {

	public static String toStringX(Object obj) {
		return obj != null ? obj.toString() : null;
	}

	public static Integer parseIntX(Object obj) {
		return obj != null ? Integer.parseInt(obj.toString()) : null;
	}
}
