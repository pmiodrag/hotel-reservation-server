package com.twinsoft.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.twinsoft.domain.HotelRoomType;

/**
 * The hotel room repository.
 *
 * @author miodrag
 */
@Repository
public interface HotelRoomRepository extends CrudRepository<HotelRoomType, Long> {

}
