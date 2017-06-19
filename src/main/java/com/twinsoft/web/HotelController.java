/**
 * 
 */
package com.twinsoft.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.twinsoft.domain.Hotel;
import com.twinsoft.service.HotelService;

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
	

	/**
	 * Rest endpoint for retrieving all hotel.
	 *
	 * @param pageable
	 * @return ResponseEntity<Page<Hotel>>
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Hotel>> findAll() {
		return new ResponseEntity<>(hotelService.findAll(), HttpStatus.OK);
	}
}
