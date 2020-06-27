package com.sayan.fullstack.demospringbootangular.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sayan.fullstack.demospringbootangular.entity.RoomEntity;

public interface RoomRepository extends PagingAndSortingRepository<RoomEntity, Long>{
	Page<RoomEntity> findById(Long id, Pageable page);
	
	Optional<RoomEntity> findById(Long id);
	
	//RoomEntity findById(Long id);
}
