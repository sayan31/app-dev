package com.sayan.fullstack.demospringbootangular.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.sayan.fullstack.demospringbootangular.converter.ReservationEntityToReservationResponseConverter;
import com.sayan.fullstack.demospringbootangular.converter.ReservationRequestToReservationEntityConverter;
import com.sayan.fullstack.demospringbootangular.converter.RoomEntityToReservableRoomResponseConverter;

/**
 * @author S
 * ConversionConfig registers converters that allows
 * application to convert request/response objects 
 * into entities & vice-versa 
 */
@Configuration
public class ConversionConfig {
	
	private Set<Converter> getConverters(){
		Set<Converter> converters = new HashSet<Converter>();
		converters.add(new RoomEntityToReservableRoomResponseConverter());
		converters.add(new ReservationRequestToReservationEntityConverter());
		converters.add(new ReservationEntityToReservationResponseConverter());
		return converters;
	}
	
	@Bean
	public ConversionService conversionService() {
		ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
		conversionServiceFactoryBean.setConverters(getConverters());
		conversionServiceFactoryBean.afterPropertiesSet();
		
		return conversionServiceFactoryBean.getObject();
	}
}
