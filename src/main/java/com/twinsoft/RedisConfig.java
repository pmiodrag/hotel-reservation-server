/**
 *
 */
package com.twinsoft;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.twinsoft.domain.Hotel;

/**
 * The Class RedisConfig.
 *
 * @author Miodrag Pavkovic
 */
@Configuration
public class RedisConfig {
	
	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    /** The connection factory. */
    @Inject
    private JedisConnectionFactory connectionFactory;

    
    /**
     * Redis template used for handling hotel
     *
     * @return the redis template
     */
    @Bean(name = "hotelRedisTemplate")
    public RedisTemplate<String, Hotel> hotelRedisTemplate() {
        final RedisTemplate<String, Hotel> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new GenericToStringSerializer<String>(String.class));
        final Jackson2JsonRedisSerializer<Hotel> serializer = new Jackson2JsonRedisSerializer<>(
        		Hotel.class);
        serializer.setObjectMapper(redisObjectMapper());
        redisTemplate.setValueSerializer(serializer);
        return redisTemplate;
    }

    /**
     * Redis object mapper.
     *
     * @return the object mapper
     */
    @Bean(name = "redisObjectMapper")
    public ObjectMapper redisObjectMapper() {
        final ObjectMapper redisObjectMapper = new ObjectMapper();
        redisObjectMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
        redisObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        redisObjectMapper.registerModule(new JavaTimeModule());
        redisObjectMapper.setDateFormat(new SimpleDateFormat(TIMESTAMP_FORMAT));
        return redisObjectMapper;
    }
}
