package com.twinsoft.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * The hotel room repository.
 *
 * @author miodrag
 */
@Repository
public interface HotelRoomRepository extends PagingAndSortingRepository<HotelRoom, Long> {

}
