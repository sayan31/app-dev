package com.sayan.fullstack.demospringbootangular.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sayan.fullstack.demospringbootangular.entity.ReservationEntity;
import com.sayan.fullstack.demospringbootangular.model.request.ReservationRequest;

@Component
public class ReservationRequestToReservationEntityConverter implements Converter<ReservationRequest,ReservationEntity>{

	@Override
	public ReservationEntity convert(ReservationRequest source) {
		ReservationEntity reservationEntity = new ReservationEntity();
		reservationEntity.setCheckin(source.getCheckIn());
		reservationEntity.setCheckout(source.getCheckOut());
		//reservationEntity.setRoomEntity(new RoomEntity());
		if(null!=source.getId()) {
			reservationEntity.setId(source.getId());
		}
		return reservationEntity;
	}
	
}
