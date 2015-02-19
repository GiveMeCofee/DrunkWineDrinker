package com.powerbot.winedrinker;

public enum Jugs {
	WINE(1993), EMPTY(1935), WATER(1937);

	private final int code;

	Jugs(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
