package com.twinsoft.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.twinsoft.domain.HotelRoomType;

/**
 * The HotelRoomType repository.
 *
 * @author miodrag
 */
@Repository
public interface HotelRoomTypeRepository extends CrudRepository<HotelRoomType, Long> {

}
