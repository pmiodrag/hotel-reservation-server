/**
 * 
 */
package com.twinsoft.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.twinsoft.domain.HotelReservation;

/**
 * @author miodrag
 *
 */
@Repository
public interface HotelReservationRepository extends CrudRepository<HotelReservation, Long> {

}
