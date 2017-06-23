/**
 *
 */
package com.twinsoft.util.caching;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility class to handle Redis cache keys
 *
 * @author Miodrag Pavkovic
 */
@Slf4j
public class HotelCacheKeyUtil {

    private static final String KEY_PARTS_DELIMITER = "|";

    private static final String KEY_VALUE_DELIMITER = ":";

    private static final String HOTEL_ROOT_KEY_PART = "hotel" + KEY_VALUE_DELIMITER;

    private HotelCacheKeyUtil() {
        // Private consturctor for util class
    }

    public static enum KeyType {

        HOTEL("hotel");

        private String type;

        KeyType(final String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * Create unique key value for hotel cacheable beans based on long value </br>
     *
     * @param first
     *            first integer value
     * @param type
     *            key type is hotel
     * @return unique key redis cache
     */
    public static String generateHotelKey(final Long first, final KeyType type) {
        final String key = HOTEL_ROOT_KEY_PART + first + KEY_PARTS_DELIMITER + type.getType();
        log.info("Generated redis key: {}", key);
        return key;
    }
}
