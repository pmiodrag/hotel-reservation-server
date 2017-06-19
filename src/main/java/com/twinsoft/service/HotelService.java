/**
 * 
 */
package com.twinsoft.service;

import java.util.List;

import com.twinsoft.domain.Hotel;

/**
 * HotelService interface
 * 
 * @author miodrag 
 */
public interface HotelService {
	/**
	 * @return
	 */
	List<Hotel> findAll();
	
	/**
	 * @return
	 */
	Hotel save(Hotel hotel);
	
}
