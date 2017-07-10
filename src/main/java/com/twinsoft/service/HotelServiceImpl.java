/**
 * 
 */
package com.twinsoft.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.twinsoft.domain.Hotel;
import com.twinsoft.repository.HotelRepository;
import com.twinsoft.util.caching.HotelCacheKeyUtil;
import com.twinsoft.util.caching.HotelCacheableBase;

import lombok.extern.slf4j.Slf4j;


/**
 * Implementation of hotel service.
 * 
 * @author Miodrag Pavkovic
 */
@Service
@Slf4j
public class HotelServiceImpl implements HotelService {
	
	/** The not found message */
	private static final String RESOURCE_NOT_FOUND_MESSAGE = "resourceNotFound";
	
	private final RedisTemplate<String, Hotel> redisTemplate;

    private final Long cacheTtl;
	
	private final HotelRepository repository;
	
	@Inject
	public HotelServiceImpl(HotelRepository repository, @Qualifier("hotelRedisTemplate") final RedisTemplate<String, Hotel> redisTemplate,
		    @Value("${spring.redis.hotel.cache-ttl}") final Long cacheTtl) {
		this.repository = repository;
		this.redisTemplate = redisTemplate;
		this.cacheTtl = cacheTtl;
	}
	
	/* (non-Javadoc)
	 * @see com.twinsoft.service.HotelService#findAll()
	 */
	@Override
	public List<Hotel> findAll() {
		return repository.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.twinsoft.service.HotelService#save(com.twinsoft.domain.Hotel)
	 */
	@Override
	public Hotel save(Hotel hotel) {
		final Hotel savedHotel = repository.save(hotel);
		final String key = HotelCacheKeyUtil.generateHotelKey(hotel.getId(), HotelCacheKeyUtil.KeyType.HOTEL);
	    saveToCache(key, savedHotel);
		return savedHotel;
	}

	/* (non-Javadoc)
	 * @see com.twinsoft.service.HotelService#delete(java.lang.String)
	 */
	@Override
	public void delete(final Long id) {
	    evictFromCache(HotelCacheKeyUtil.generateHotelKey(id, HotelCacheKeyUtil.KeyType.HOTEL));
		repository.delete(id);
		
	}

	/* (non-Javadoc)
	 * @see com.twinsoft.service.HotelService#findOne(java.lang.String)
	 */
	@Override
	public Hotel findByHotelId(Long id) {
		return repository.findById(id);
	}

    @Override
    public Optional<HotelCacheableBase> findByIdFromCache(final String id) {
        try {
            return Optional.ofNullable(redisTemplate.opsForValue()
                .get(id));
        } catch (final Exception e) {
            log.error("Could not retrieve run parameter for hotel " + id + " from cache. Cause: ", e);
            return Optional.empty();
        }
    }

    @Override
    public void saveToCache(final String id, final HotelCacheableBase cacheable) {
        if (cacheable.getClass()
            .isInstance(new Hotel())) {
            final Hotel hotelToCache = (Hotel) cacheable;
            try {
                redisTemplate.opsForValue()
                    .set(id, hotelToCache, cacheTtl, TimeUnit.MINUTES);
                log.info("Hotel {} saved in cache", new Object[] { hotelToCache });
            } catch (final Exception e) {
                log.error("Hotel cache" + hotelToCache + " for hotel " + id + " could not be saved to cache. Cause: ",
                        e);
            }
        }
    }

    @Override
    public void evictFromCache(final String id) {
        try {
            redisTemplate.delete(id);
            log.info("Hotel with id {} evicted from cache", new Object[] { id });
        } catch (final Exception e) {
            log.error("Hotel  with cache id " + id + " could not be eviced. Cause: ", e);
        }
    }

	@Override
	public Hotel update(Hotel hotel) {
		return repository.save(hotel);
	}
 
}
