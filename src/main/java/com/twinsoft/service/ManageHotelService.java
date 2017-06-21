/**
 * 
 */
package com.twinsoft.service;

import java.util.Map;

import com.twinsoft.domain.Hotel;
import com.twinsoft.domain.HotelRating;
import com.twinsoft.domain.RoomType;

/**
 * @author Miodrag Pavkovic
 */
public interface ManageHotelService {

	/**
	 * @param roomType
	 * @param hotelRating
	 * @return
	 */
	Map<Hotel, Boolean> checkHotelsAvailableRooms(RoomType roomType, HotelRating hotelRating);

	/**
	 * @param hotel
	 * @param roomType
	 * @param hotelRating
	 * @return
	 */
	boolean checkHotelAvailableRooms(Hotel hotel, RoomType roomType, HotelRating hotelRating);

}
