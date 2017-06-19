package com.twinsoft.domain;

/**
 * @author miodrag
 *
 */
public enum RoomType {
	SINGLE("Single"),
	DOUBLE("Double");
	private final String value;
    private RoomType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
