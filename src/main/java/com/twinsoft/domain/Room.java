package com.twinsoft.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room implements Serializable {

	private static final long serialVersionUID = 1038141457256654585L;
	
	/** The room id. */
    @Id
    @GeneratedValue
    private Long id;
    
    /** The hotel. */
	@ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    
    /** The room type. */
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    
    @NotNull
    @Min(value = 0, message = "priceCannotBeLessThanZero")
    private BigDecimal price;
    
}
