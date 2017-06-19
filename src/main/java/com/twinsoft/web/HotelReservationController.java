/**
 * 
 */
package com.twinsoft.web;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.TransactionRequiredException;
import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.twinsoft.domain.HotelReservation;
import com.twinsoft.service.HotelReservationService;
import com.twinsoft.util.exception.DeleteEntityException;
import com.twinsoft.util.exception.PersistEntityException;
import com.twinsoft.util.exception.ResourceNotFoundException;
import com.twinsoft.util.exception.UpdateEntityException;

import lombok.extern.slf4j.Slf4j;
/**
 * Rest controller for exposing hotel reservation endpoints.
 * @author miodrag
 */
@Slf4j
@RestController
@RequestMapping("hotelreservations")
public class HotelReservationController {
	private static final String RESOURCE_NOT_FOUND_MESSAGE = null;
	/** The hotel service */
	private final HotelReservationService hotelReservationService;

	@Inject
	public HotelReservationController(final HotelReservationService hotelReservationService) {
		this.hotelReservationService = hotelReservationService;
	}
	

	/**
	 * Rest endpoint for retrieving all hotel reservations.
	 *
	 * @return ResponseEntity<Page<Hotel>>
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HotelReservation>> findAll() {
		return new ResponseEntity<>(hotelReservationService.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Rest endpoint for creating and saving hotel reservation.
	 * 
	 * @param hotelReservation
	 * @param builder
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public HttpHeaders create(@Valid @RequestBody final HotelReservation hotelReservation, final UriComponentsBuilder builder) {		
		try {
			final HotelReservation newHotelReservation = hotelReservationService.save(hotelReservation);
			final HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(builder.path("/hotelreservations/{hotelReservationId}").buildAndExpand(newHotelReservation.getId()).toUri());
			return httpHeaders;
		} catch (IllegalArgumentException | TransactionRequiredException e) {
			throw new PersistEntityException("createMeterReadingError");
		}
	}
	

	/**
	 * Rest endpoint for updating a hotel reservation with reservation id.
	 *
	 * @param hotelReservationId
	 * @param hotelReservation
	 * @return
	 */
	@PutMapping(value="/{hotelReservationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelReservation> update(@PathVariable final Long hotelReservationId, @Valid @RequestBody final HotelReservation hotelReservation) {		
		try {
			hotelReservation.setId(hotelReservationId);
			final HotelReservation updateHotelReservation  = hotelReservationService.save(hotelReservation);
			return new ResponseEntity<>(updateHotelReservation, HttpStatus.OK);
		} catch (IllegalArgumentException | TransactionRequiredException e) {
			log.error("Exception occurred while updating hotel reservation with id {}. Cause: ", hotelReservationId, e);
			throw new UpdateEntityException();
		}
	}
	/**
	 * Rest endpoint for deleting a hotel reservation with reservation id.
	 *
	 * @param hotelReservationId
	 */
	@DeleteMapping(value = "/{hotelReservationId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("hotelReservationId") final Long hotelReservationId) {
		final HotelReservation hotelReservation = Optional
				.ofNullable(hotelReservationService.findByHoteReservationlId(hotelReservationId))
				.orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE));
		try {
			hotelReservationService.delete(hotelReservation.getId());
		} catch (final DataIntegrityViolationException e) {
			log.error("Exception occurred while deleting hotel reservation with reservation id {}. Cause: ", hotelReservationId, e);
			throw new DeleteEntityException("deleteError");
		}
	}
}
