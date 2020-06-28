package com.sayan.fullstack.demospringbootangular.model.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


public class ReservationRequest {
	
	private Long id;
	private Long roomId;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate checkIn;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate checkOut;

	
	
	
	public ReservationRequest() {
		super();
	}
	
	public ReservationRequest(Long id, Long roomId, LocalDate checkIn, LocalDate checkOut) {
		super();
		this.id=id;
		this.roomId = roomId;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	public LocalDate getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	
	
}
