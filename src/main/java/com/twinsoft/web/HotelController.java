/**
 * 
 */
package com.twinsoft.web;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TransactionRequiredException;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.twinsoft.domain.Hotel;
import com.twinsoft.service.HotelService;
import com.twinsoft.util.exception.PersistEntityException;

import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller for exposing Hotel endpoints.
 * @author miodrag
 */
@Slf4j
@RestController
@RequestMapping("hotels")
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
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public HttpHeaders create(@Valid @RequestBody final Hotel hotel, final BindingResult errors,
			final UriComponentsBuilder builder) {
		
		try {
			final Hotel newHotel = hotelService.save(hotel);
			final HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(builder.path("/hotels/{hotelId}").buildAndExpand(hotel.getId()).toUri());
			return httpHeaders;
		} catch (IllegalArgumentException | TransactionRequiredException e) {
			throw new PersistEntityException("createMeterReadingError");
		}
	}
}
