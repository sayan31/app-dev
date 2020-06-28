package com.sayan.fullstack.demospringbootangular.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sayan.fullstack.demospringbootangular.entity.RoomEntity;
import com.sayan.fullstack.demospringbootangular.model.Links;
import com.sayan.fullstack.demospringbootangular.model.Self;
import com.sayan.fullstack.demospringbootangular.model.response.ReservableRoomResponse;
import com.sayan.fullstack.demospringbootangular.rest.ResourceConstants;

@Component
public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity,ReservableRoomResponse>{

	@Override
	public ReservableRoomResponse convert(RoomEntity source) {
		ReservableRoomResponse reservationResponse = new ReservableRoomResponse();
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
