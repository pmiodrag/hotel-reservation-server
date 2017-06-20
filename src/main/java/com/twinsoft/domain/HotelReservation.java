package com.twinsoft.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
public class HotelReservation implements Serializable {

	private static final long serialVersionUID = 2117514065166401617L;

	/** The hotel reservation id. */
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    
    /** Hotel room type. */
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    
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
