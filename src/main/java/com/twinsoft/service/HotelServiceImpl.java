/**
 * 
 */
package com.twinsoft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.twinsoft.domain.Hotel;
import com.twinsoft.repository.HotelRepository;


/**
 * Implementation of hotel service.
 * 
 * @author Miodrag Pavkovic
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
		return repository.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.twinsoft.service.HotelService#save(com.twinsoft.domain.Hotel)
	 */
	@Override
	public Hotel save(Hotel hotel) {
		return repository.save(hotel);
	}

	/* (non-Javadoc)
	 * @see com.twinsoft.service.HotelService#delete(java.lang.String)
	 */
	@Override
	public void delete(final Long id) {
		repository.delete(id);
		
	}

	/* (non-Javadoc)
	 * @see com.twinsoft.service.HotelService#findOne(java.lang.String)
	 */
	@Override
	public Hotel findByHotelId(Long id) {
		return repository.findById(id);
	}
 
}
