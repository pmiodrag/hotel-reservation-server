package com.twinsoft.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author miodrag
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
@Builder
public class Hotel implements Serializable {
	
private static final long serialVersionUID = 8690390386555199353L;
    
    /** The hotel id. */
    @Id
    @GeneratedValue
    private Long id;
    
    /** Number of rooms **/
    @NotNull
    private Integer totalRooms;
    
    /** Hotel rating - stars **/
    @NotNull
    @Enumerated(EnumType.STRING)
    private HotelRating rating;
    
    /** The hotel room types. */
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<HotelRoomType> hotelRoomTypes;

}
