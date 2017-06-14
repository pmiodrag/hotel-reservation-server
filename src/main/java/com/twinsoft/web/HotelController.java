/**
 * 
 */
package com.twinsoft.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twinsoft.service.HotelService;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller for exposing Hotel endpoints.
 * @author miodrag
 */
@Slf4j
@RestController
@RequestMapping("hotel")
public class HotelController {
	/** The hotel service */
	private final HotelService hotelService;

	@Inject
	public HotelController(final HotelService hotelService) {
		this.hotelService = hotelService;
	}
}
