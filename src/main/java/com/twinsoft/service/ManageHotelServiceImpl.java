/**
 * 
 */
package com.twinsoft.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.twinsoft.domain.Hotel;
import com.twinsoft.domain.HotelRating;
import com.twinsoft.domain.HotelReservation;
import com.twinsoft.domain.HotelRoomType;
import com.twinsoft.domain.RoomType;
import com.twinsoft.repository.HotelRepository;
import com.twinsoft.repository.HotelReservationRepository;
import com.twinsoft.repository.HotelRoomRepository;
import com.twinsoft.service.ManageHotelService;

/**
 * @author Miodrag Pavkovic
 */
@Service
public class ManageHotelServiceImpl implements ManageHotelService {

	private final HotelRepository hotelRepository;
	private final HotelReservationRepository hotelReservationRepository;
	/**
	 * @param hotelRepository
	 * @param hotelReservationRepository
	 */
	@Inject
	public ManageHotelServiceImpl(HotelRepository hotelRepository,
			HotelReservationRepository hotelReservationRepository) {
		this.hotelRepository = hotelRepository;
		this.hotelReservationRepository = hotelReservationRepository;
	}
	
	@Override
	public boolean checkHotelAvailableRooms(Hotel hotel, RoomType roomType, HotelRating hotelRating) {
		Predicate<HotelRoomType> checkRoomType = hotelRoomType -> hotelRoomType.getRoomType().equals(roomType);
		List<HotelReservation> hotelReservations = getMatchingHotelReservations(hotel, roomType);
		List<HotelRoomType> matchingHotelRoomTypes = hotel.getHotelRoomTypes().stream().filter(checkRoomType).collect(Collectors.toList());
		if (hotelReservations.size() < matchingHotelRoomTypes.size() ) {
			return true;
		}
		return false;
	}

	/**
	 * @param hotel
	 * @param roomType
	 * @return
	 */
	private List<HotelReservation> getMatchingHotelReservations(Hotel hotel, RoomType roomType) {
		Predicate<HotelReservation> matchHotelId = hotelReservation -> hotelReservation.getHotel().getId().equals(hotel.getId());
		Predicate<HotelReservation> matchRoomType = hotelReservation -> hotelReservation.getRoomType().equals(roomType);	
		List<HotelReservation> reservations = hotelReservationRepository.findAllByStartDateBeforeAndEndDateAfter(LocalDate.now());
		//  All reservations for matching hotel, room type and date of reservation at current date
		return reservations.stream().filter(matchHotelId).filter(matchRoomType).collect(Collectors.toList());
	}

	/* (non-Javadoc)
	 * @see com.twinsoft.service.ManageHotelService#checkHotelAvailableRooms(com.twinsoft.domain.RoomType, com.twinsoft.domain.HotelRating)
	 */
	@Override
	public Map<Hotel, Boolean> checkHotelsAvailableRooms(RoomType roomType, HotelRating hotelRating) {
		List<Hotel> hotels = hotelRepository.findAll();
		return hotels.stream()
				.collect(Collectors.toMap(hotel -> hotel, hotel -> checkHotelAvailableRooms(hotel, roomType, hotelRating)));
		
	}

}
