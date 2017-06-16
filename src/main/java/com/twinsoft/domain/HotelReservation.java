package com.twinsoft.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class HotelReservation implements Serializable {

	private static final long serialVersionUID = 2117514065166401617L;

	/** The hotel id. */
    @Id
    @GeneratedValue
    private Long id;
    
    /** Hotel room type. */
    @NotNull
    private RoomTypeEnum roomType;
    
    /** Reservation start date */
    @NotNull
    private LocalDate startDate;
    
    /** Reservation end date */
    @NotNull
    private LocalDate endDate;
    
    /** Reservation price calculated by date of reservation. */
    @NotNull
    @Min(value = 0, message = "priceCannotBeLessThanZero")
    private BigDecimal reservationPrice;
    
}
