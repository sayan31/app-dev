package com.sayan.fullstack.demospringbootangular.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sayan.fullstack.demospringbootangular.entity.RoomEntity;
import com.sayan.fullstack.demospringbootangular.model.Links;
import com.sayan.fullstack.demospringbootangular.model.Self;
import com.sayan.fullstack.demospringbootangular.model.response.ReservationResponse;
import com.sayan.fullstack.demospringbootangular.rest.ResourceConstants;

@Component
public class RoomEntityToReservationResponseConverter implements Converter<RoomEntity,ReservationResponse>{

	@Override
	public ReservationResponse convert(RoomEntity source) {
		ReservationResponse reservationResponse = new ReservationResponse();
		reservationResponse.setRoomNumber(source.getRoomNumber());
		reservationResponse.setPrice(Integer.valueOf(source.getPrice()));
		
		Links links = new Links();
		Self self = new Self();
		self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());
		links.setSelf(self);
		
		reservationResponse.setLinks(links);
		
		return reservationResponse;
	}

}
