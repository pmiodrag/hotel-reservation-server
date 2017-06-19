/**
 * 
 */
package com.twinsoft.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.twinsoft.domain.HotelReservation;

/**
 * @author miodrag
 *
 */
@Repository
public interface HotelReservationRepository extends CrudRepository<HotelReservation, Long> {
	List<HotelReservation> findAll();
	HotelReservation findById(@Param("hotelreservationid") Long hotelreservationid);
}
