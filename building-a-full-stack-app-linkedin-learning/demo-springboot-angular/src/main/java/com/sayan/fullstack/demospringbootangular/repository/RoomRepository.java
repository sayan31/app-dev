package com.sayan.fullstack.demospringbootangular.repository;

//import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sayan.fullstack.demospringbootangular.entity.RoomEntity;

public interface RoomRepository extends CrudRepository<RoomEntity, Long>{
	Optional<RoomEntity> findById(Long id); 
}
