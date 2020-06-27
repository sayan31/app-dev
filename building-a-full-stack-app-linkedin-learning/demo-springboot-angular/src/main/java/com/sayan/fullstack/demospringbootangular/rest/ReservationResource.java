package com.sayan.fullstack.demospringbootangular.rest;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sayan.fullstack.demospringbootangular.converter.RoomEntityToReservationResponseConverter;
import com.sayan.fullstack.demospringbootangular.entity.RoomEntity;
import com.sayan.fullstack.demospringbootangular.model.request.ReservationRequest;
import com.sayan.fullstack.demospringbootangular.model.response.ReservationResponse;
import com.sayan.fullstack.demospringbootangular.repository.RoomRepository;



/**
 * @author S
 * @RestController combines 2 annotations:
 * @Controller & @ResponseBody
 * @Controller - allows impl classes to be autodetected through classpath
 *             scanning.
 * @ResponseBody - placed on each request handling method to enable automatic
 *               serialization of the returned object into HttpResponse.
 */
@RestController

/**
 * @RequestMapping - is used to map incoming requests to correct controllers The
 *                 mapping can be done in various ways: 1. by path value: put
 *                 the exact path to the resource in the "value" attribute. 2.
 *                 specify the HTTP method type to use for the request in the
 *                 "method" attribute. 3. specify the produces and consumes
 *                 attributes to restrict media types produced & consumed by a
 *                 controller
 */
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
public class ReservationResource {
	
	@Autowired
	private RoomRepository roomRespository;
	
	@Autowired
	private RoomEntityToReservationResponseConverter roomEntityToReservationResponseConverter;
	
	@RequestMapping(path="",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<ReservationResponse> getAvailableRooms(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
			Pageable pageable) {
		
		Page<RoomEntity> listOfAvailableRooms=roomRespository.findAll(pageable);
		
		//return new ResponseEntity<>(new ReservationResponse(), HttpStatus.OK);
		return listOfAvailableRooms.map(roomEntityToReservationResponseConverter::convert);
	}
	
	@RequestMapping(path="/{roomId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoomEntity> getRoomById(
			@PathVariable Long roomId){
		
		Optional<RoomEntity> roomEntity=roomRespository.findById(roomId);
		return new ResponseEntity<RoomEntity>(roomEntity.get(), HttpStatus.OK);
	}
	
	@RequestMapping(path="",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationResponse> createReservation(
			@RequestBody ReservationRequest reservationRequest){
		
		return new ResponseEntity<>(new ReservationResponse(), HttpStatus.CREATED);
	}
	
	@RequestMapping(path="",method=RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationResponse> updateReservation(
			@RequestBody ReservationRequest reservationRequest){
		
		return new ResponseEntity<>(new ReservationResponse(), HttpStatus.OK);
	}
	
	@RequestMapping(path="/{reservationId}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId){
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
