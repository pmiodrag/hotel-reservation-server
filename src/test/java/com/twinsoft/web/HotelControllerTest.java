/**
 * 
 */
package com.twinsoft.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.TransactionRequiredException;
import javax.validation.Valid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.common.collect.Lists;
import com.twinsoft.domain.Hotel;
import com.twinsoft.domain.HotelRating;
import com.twinsoft.domain.HotelRoomType;
import com.twinsoft.domain.RoomType;
import com.twinsoft.service.HotelRoomService;
import com.twinsoft.service.HotelService;
import com.twinsoft.service.ManageHotelService;
import com.twinsoft.util.event.EventType;
import com.twinsoft.util.exception.UpdateEntityException;

/**
 * @author Miodrag Pavkovic
 */
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
@WebMvcTest(controllers = { HotelController.class }, secure = false)
public class HotelControllerTest extends AbstractRestControllerTest {
	
	@Inject
    private MockMvc mockMvc;	
	@Inject
	private HotelService hotelService;
	@Inject
	private HotelRoomService roomService;
	@Inject
	private ManageHotelService manageHotelService;
	
	/**
	 * @throws Exception
	 */
	@Test
    public void testFindAllHotels() throws Exception {    	
		final Hotel firstHotel = Hotel.builder().id(1L).name("Rossa De Mar").rating( HotelRating.THREE_STAR).totalRooms(Integer.valueOf(2)).build();	
		final Hotel secondHotel = Hotel.builder().id(2L).name("Rossa De Mar 2").rating( HotelRating.FOUR_STAR).totalRooms(Integer.valueOf(2)).build();
        when(hotelService.findAll()).thenReturn(Lists.newArrayList(firstHotel, secondHotel));
        mockMvc.perform(get("/api/hotels").accept(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].name", is("Rossa De Mar")))
        .andExpect(jsonPath("$[0].rating", is(HotelRating.THREE_STAR.toString())))
        .andExpect(jsonPath("$[0].totalRooms", is(2)))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[1].name", is("Rossa De Mar 2")))
        .andExpect(jsonPath("$[1].rating", is(HotelRating.FOUR_STAR.toString())))
        .andExpect(jsonPath("$[1].totalRooms", is(2)));     

    	verify(hotelService, times(1)).findAll();
//    	verifyNoMoreInteractions(hotelService);
    }	

	
	@Test
    public void testSaveHotel() throws Exception {    	
		final Hotel hotel = Hotel.builder().name("Rossa De Mar").rating( HotelRating.THREE_STAR).totalRooms(Integer.valueOf(2)).build();	
		final HotelRoomType hotelRoomType = HotelRoomType.builder().roomType(RoomType.SINGLE).price(BigDecimal.valueOf(100.00)).build();
		final List<HotelRoomType> hotelRoomTypes = Lists.newArrayList(hotelRoomType);
		hotel.setHotelRoomTypes(hotelRoomTypes);
		// Mock Hotel entity after saving
		final Hotel savedHotel = Hotel.builder().id(1L).name("Rossa De Mar").rating( HotelRating.THREE_STAR).totalRooms(Integer.valueOf(2)).build();	
        when(hotelService.save(hotel)).thenReturn(savedHotel);
        final HotelRoomType newHotelRoomType = HotelRoomType.builder().hotel(savedHotel).roomType(RoomType.SINGLE).price(BigDecimal.valueOf(100.00)).build();
        // Mock HotelRoomType entity after saving, set id
		final HotelRoomType savedHotelRoomType = HotelRoomType.builder().id(1L).hotel(savedHotel).roomType(RoomType.SINGLE).price(BigDecimal.valueOf(100.00)).build();
		when(roomService.save(newHotelRoomType)).thenReturn(savedHotelRoomType);
		savedHotel.setHotelRoomTypes(Lists.newArrayList(savedHotelRoomType));

        mockMvc.perform(post("/api/hotels")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(hotel))
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isCreated());
                
    	verify(hotelService, times(1)).save(hotel);
    	verifyNoMoreInteractions(hotelService);
    }
	
	@Test
	public void testUpdateHotel() throws Exception {   
		final Hotel oldHotel = Hotel.builder().name("Rossa De Mar").rating( HotelRating.THREE_STAR).totalRooms(Integer.valueOf(2)).build();	
//		try {
//			hotel.setId(hotelId);
//			final Hotel updatedHotel = hotelService.save(hotel);
//			publishHotelEvent(updatedHotel, EventType.UPDATE);
//			return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
//		} catch (IllegalArgumentException | TransactionRequiredException e) {
//			log.error("Exception occurred while updating hotel with id {}. Cause: ", hotelId, e);
//			throw new UpdateEntityException();
//		}
	}
	/**
	 * @return
	 */
	private List<HotelRoomType> getHotelRoomTypes() {
		return Lists.newArrayList(createHotelRoomType());
	}
	
	/**
	 * @return
	 */
	private HotelRoomType createHotelRoomType() {
		return new HotelRoomType(1L, new Hotel(), RoomType.SINGLE, BigDecimal.valueOf(100.00));
	}


}
