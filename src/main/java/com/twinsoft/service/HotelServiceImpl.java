/**
 * 
 */
package com.twinsoft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.twinsoft.domain.Hotel;
import com.twinsoft.repository.HotelRepository;

/**
 * @author miodrag
 *
 */
@Service
public class HotelServiceImpl implements HotelService {
	
	/** The not found message */
	private static final String RESOURCE_NOT_FOUND_MESSAGE = "resourceNotFound";
	private final HotelRepository repository;
	
	public HotelServiceImpl(HotelRepository repository) {
		this.repository = repository;
	}
	
	/* (non-Javadoc)
	 * @see com.twinsoft.service.HotelService#findAll()
	 */
	@Override
	public List<Hotel> findAll() {
		List<Hotel> hotels = repository.findAll();
		return hotels;
	}
	
	/* (non-Javadoc)
	 * @see com.twinsoft.service.HotelService#save(com.twinsoft.domain.Hotel)
	 */
	@Override
	public Hotel save(Hotel hotel) {
		return repository.save(hotel);
	}
 
}
