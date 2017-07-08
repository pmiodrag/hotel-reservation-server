/**
 * 
 */
package com.twinsoft.web;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.google.common.collect.Lists;
import com.twinsoft.domain.Hotel;
import com.twinsoft.domain.HotelRating;
import com.twinsoft.domain.HotelRoomType;
import com.twinsoft.domain.RoomType;
import com.twinsoft.service.HotelService;
import com.twinsoft.service.ManageHotelService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

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
//        .andExpect(jsonPath("$[0].rating", is(HotelRating.THREE_STAR)))
        .andExpect(jsonPath("$[0].totalRooms", is(2)))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[1].name", is("Rossa De Mar 2")))
//        .andExpect(jsonPath("$[1].rating", is(HotelRating.FOUR_STAR)))
        .andExpect(jsonPath("$[1].totalRooms", is(2)));     

    	verify(hotelService, times(1)).findAll();
    	verifyNoMoreInteractions(hotelService);
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
