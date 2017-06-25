/**
 *
 */
package com.twinsoft.util.caching;

import java.util.Optional;

/**
 * Base caching service defining methods to interact with Redis cache
 *
 * @author Miodrag Pavkovic
 */
public interface HotelCacheServiceBase {

    /**
     * Retrieve cacheable bean saved in Redis cache
     *
     * @param id
     *            identifier in cache
     * @return value from cache by id if found
     */
    Optional<HotelCacheableBase> findByIdFromCache(String id);

    /**
     * Save cacheable bean to Redis cache
     *
     * @param id
     *            identifier in cache
     * @param cacheable
     *            cacheable bean to be save to cache
     */
    void saveToCache(String id, HotelCacheableBase cacheable);

    /**
     * Evict value from Redis cache
     *
     * @param id
     *            identifier in cache
     */
    void evictFromCache(String id);
}
