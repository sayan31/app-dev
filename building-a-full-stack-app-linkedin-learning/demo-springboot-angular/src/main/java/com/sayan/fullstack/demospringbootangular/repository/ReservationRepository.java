package com.sayan.fullstack.demospringbootangular.repository;

import org.springframework.data.repository.CrudRepository;

import com.sayan.fullstack.demospringbootangular.entity.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity,Long>{
	
}
