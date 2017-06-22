/**
 * 
 */
package com.twinsoft.service;

import java.util.List;
import java.util.Map;

import com.twinsoft.domain.Hotel;
import com.twinsoft.domain.HotelRating;
import com.twinsoft.domain.HotelReservation;
import com.twinsoft.domain.HotelRoomType;
import com.twinsoft.domain.RoomType;

/**
 * @author Miodrag Pavkovic
 */
public interface ManageHotelService {

	/**
	 * Allow to check if the hotel has available rooms by specifying room type and hotel stars (rating).
	 * Method return result in a map containing values for each hotel.
	 * 
	 * @param roomType
	 * @param hotelRating
	 * @return
	 */
	Map<Hotel, Boolean> checkHotelsAvailableRooms(RoomType roomType, HotelRating hotelRating);

	/**
	 * Check if the hotel has available rooms by specifying room tyope and hotel stars (rating).
	 * 
	 * @param hotel
	 * @param roomType
	 * @param hotelRating
	 * @return
	 */
	boolean checkHotelAvailableRooms(Hotel hotel, RoomType roomType, HotelRating hotelRating);
	

	/**
	 * Allow to check if the hotel has available rooms by specifying room type and hotel stars (rating).
	 * Method return result in a map containing values for each hotel.
	 * 
	 * @return
	 */
	Map<Hotel, Boolean> HotelsReservationsSummary();
	
	/**
	 * Method return available rooms  for given hotel.
	 * 
	 * @return
	 */
	Map<Hotel, List<HotelRoomType>> availableHotelRooms();
	
	/**
	 * Method return reserved rooms  for given hotel.
	 * 
	 * @return
	 */
	Map<Hotel, List<HotelRoomType>> hotelReservedRooms();

	/**
	 * Method return total rooms  for given hotel.
	 * 
	 * @return result in map of hotel as a key and a List of total rooms for that hotel as a value
	 */
	Map<Hotel, List<HotelRoomType>> hotelTotalRooms();

}
