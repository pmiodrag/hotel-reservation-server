package com.twinsoft.event;

import lombok.Data;

@Data
public class HotelEventMessage {

	private final Long id;
	private final String eventType;
	public HotelEventMessage(Long id, String eventType) {
		this.id = id;
		this.eventType = eventType;
	}
	
}
