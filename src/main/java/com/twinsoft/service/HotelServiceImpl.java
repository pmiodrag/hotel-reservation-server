/**
 * 
 */
package com.twinsoft.service;

import java.util.List;

import javax.transaction.Transactional;

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
	@Override
	public List<Hotel> findAll() {
		return repository.findAll();
	}
 
}
