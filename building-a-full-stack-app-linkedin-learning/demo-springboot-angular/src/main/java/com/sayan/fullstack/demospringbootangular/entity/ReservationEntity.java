package com.sayan.fullstack.demospringbootangular.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Reservation")
public class ReservationEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private LocalDate checkIn;
	
	@NotNull
	private LocalDate checkOut;
	
	@ManyToOne
	private RoomEntity roomEntity;

	public ReservationEntity() {
		super();
	}

	public ReservationEntity(@NotNull LocalDate checkIn, @NotNull LocalDate checkOut, RoomEntity roomEntity) {
		super();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.roomEntity = roomEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getCheckin() {
		return checkIn;
	}

	public void setCheckin(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckout() {
		return checkOut;
	}

	public void setCheckout(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public RoomEntity getRoomEntity() {
		return roomEntity;
	}

	public void setRoomEntity(RoomEntity roomEntity) {
		this.roomEntity = roomEntity;
	}
}
