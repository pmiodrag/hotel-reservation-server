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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.common.collect.Lists;
import com.twinsoft.domain.Hotel;
import com.twinsoft.domain.HotelRating;
import com.twinsoft.domain.HotelRoomType;
import com.twinsoft.domain.RoomType;
import com.twinsoft.service.HotelRoomService;
import com.twinsoft.service.HotelService;
import com.twinsoft.service.ManageHotelService;

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
//        .andExpect(jsonPath("$[0].rating", is(HotelRating.THREE_STAR)))
        .andExpect(jsonPath("$[0].totalRooms", is(2)))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[1].name", is("Rossa De Mar 2")))
//        .andExpect(jsonPath("$[1].rating", is(HotelRating.FOUR_STAR)))
        .andExpect(jsonPath("$[1].totalRooms", is(2)));     

    	verify(hotelService, times(1)).findAll();
    	verifyNoMoreInteractions(hotelService);
    }
	
//	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseStatus(HttpStatus.CREATED)
//	public HttpHeaders create(@Valid @RequestBody final Hotel hotel, final UriComponentsBuilder builder) {		
//		try {
//			final Hotel newHotel = Hotel.builder().name(hotel.getName()).rating(hotel.getRating()).totalRooms(hotel.getTotalRooms()).build();
//			hotelService.save(newHotel);
//			// We only have data for price and room type and need to set hotel property
//			final List<HotelRoomType> oldRoomTypes = hotel.getHotelRoomTypes();		
//			final List<HotelRoomType> newRoomTypes = new ArrayList<HotelRoomType>();
//			oldRoomTypes.stream().forEach(rt -> {
//				final HotelRoomType newRoomType = HotelRoomType.builder().hotel(newHotel).price(rt.getPrice()).roomType(rt.getRoomType()).build();
//				roomService.save(newRoomType);
//				newRoomTypes.add(newRoomType);
//			});
//			newHotel.setHotelRoomTypes(newRoomTypes);	
//		    //Send a message through RabittMQ
////			publishHotelEvent(newHotel, EventType.CREATE);
//			
//			final HttpHeaders httpHeaders = new HttpHeaders();
//			httpHeaders.setLocation(builder.path("/hotels/{hotelId}").buildAndExpand(newHotel.getId()).toUri());
//			return httpHeaders;
//		} catch (IllegalArgumentException | TransactionRequiredException e) {
//			throw new PersistEntityException("createMeterReadingError");
//		}
//	}
	
	
	@Test
    public void testSaveHotel() throws Exception {    	
		final Hotel hotel = Hotel.builder().name("Rossa De Mar").rating( HotelRating.THREE_STAR).totalRooms(Integer.valueOf(2)).build();	
		final HotelRoomType hotelRoomType = HotelRoomType.builder().roomType(RoomType.SINGLE).price(BigDecimal.valueOf(100.00)).build();
		final List<HotelRoomType> hotelRoomTypes = Lists.newArrayList(hotelRoomType);
		hotel.setHotelRoomTypes(hotelRoomTypes);
		final Hotel newHotel = Hotel.builder().id(1L).name("Rossa De Mar").rating( HotelRating.THREE_STAR).totalRooms(Integer.valueOf(2)).build();	
        when(hotelService.save(hotel)).thenReturn(newHotel);
        final HotelRoomType newHotelRoomType = HotelRoomType.builder().hotel(newHotel).roomType(RoomType.SINGLE).price(BigDecimal.valueOf(100.00)).build();
//		final List<HotelRoomType> newHotelRoomTypes = Lists.newArrayList(hotelRoomType);
				
		final HotelRoomType savedHotelRoomType = HotelRoomType.builder().id(1L).hotel(newHotel).roomType(RoomType.SINGLE).price(BigDecimal.valueOf(100.00)).build();
		when(roomService.save(newHotelRoomType)).thenReturn(savedHotelRoomType);
		newHotel.setHotelRoomTypes(Lists.newArrayList(savedHotelRoomType));

        mockMvc.perform(post("/api/hotels")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(hotel))
        )
        .andExpect(status().isCreated())
//        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Rossa De Mar")))
        .andExpect(jsonPath("$.rating", is(HotelRating.THREE_STAR)))
        .andExpect(jsonPath("$.totalRooms", is(Integer.valueOf(2))));
                
    	verify(hotelService, times(1)).save(hotel);
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
