package com.twinsoft.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.twinsoft.domain.HotelRoomRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * HotelRoomService implementation.
 * 
 * @author miodrag
 */
@Service("HotelRoomService")
@Slf4j
public class HotelRoomServiceImpl implements HotelRoomService {
	
	private HotelRoomRepository repository;
	
	@Inject
	public HotelRoomServiceImpl(final HotelRoomRepository repository){
		this.repository = repository;
	}
}
