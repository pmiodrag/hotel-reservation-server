/**
 * 
 */
package com.twinsoft.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.twinsoft.domain.Hotel;

/**
 * The hotel repository.
 * 
 * @author miodrag
 *
 */
@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
	List<Hotel> findAll();
}
