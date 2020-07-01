package com.sayan.fullstack.demospringbootangular.converter;

import org.springframework.core.convert.converter.Converter;

import com.sayan.fullstack.demospringbootangular.entity.ReservationEntity;
import com.sayan.fullstack.demospringbootangular.model.response.ReservationResponse;

public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse> {

	@Override
	public ReservationResponse convert(ReservationEntity source) {
		ReservationResponse reservationResponse= new ReservationResponse();
		reservationResponse.setId(source.getId());
		reservationResponse.setCheckin(source.getCheckin());
		reservationResponse.setCheckout(source.getCheckout());
		return reservationResponse;
	}

}
