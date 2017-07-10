/**
 *
 */
package com.twinsoft.web;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.twinsoft.service.HotelReservationService;
import com.twinsoft.service.HotelRoomService;
import com.twinsoft.service.HotelService;
import com.twinsoft.service.ManageHotelService;

/**
 * @author Miodrag Pavkovic
 */
public abstract class AbstractRestControllerTest {

	@Configuration
	public static class TestConfig extends SpringDataWebConfiguration {

		/**
		 * @return HotelService mock
		 */
		@Bean
		public HotelService hotelService() {
			return mock(HotelService.class);
		}

		/**
		 * @return HotelReservationService mock
		 */
		@Bean
		public HotelReservationService hotelReservationService() {
			return mock(HotelReservationService.class);
		}
		/**
		 * @return ManageHotelService mock
		 */
		@Bean
		public ManageHotelService manageHotelService() {
			return mock(ManageHotelService.class);
		}
		
		/**
		 * @return HotelRoomService mock
		 */
		@Bean
		public HotelRoomService hotelRoomService() {
			return mock(HotelRoomService.class);
		}

		/**
		 * @return RabbitTemplate mock
		 */
		@Bean
		public RabbitTemplate rabbitTemplate() {
			return mock(RabbitTemplate.class);
		}
		
		@Bean
		public HotelController hotelController() {
			return new HotelController(hotelService(), manageHotelService(), hotelRoomService(), rabbitTemplate());
		}
		
		@Bean
		public HotelReservationController hotelReservationController () {
			return new HotelReservationController(hotelReservationService(),
				hotelService(), rabbitTemplate());					
		}

	}

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	/**
	 * Convert object to json bytes.
	 *
	 * @param object the object
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected byte[] convertObjectToJsonBytes(final Object object) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.registerModule(new JavaTimeModule());
		mapper.setDateFormat(dateFormat);
		return mapper.writeValueAsBytes(object);
	}

}
