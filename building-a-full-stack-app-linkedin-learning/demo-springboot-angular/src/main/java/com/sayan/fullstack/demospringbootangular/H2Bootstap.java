package com.sayan.fullstack.demospringbootangular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sayan.fullstack.demospringbootangular.entity.RoomEntity;
import com.sayan.fullstack.demospringbootangular.repository.RoomRepository;

@Component
public class H2Bootstap implements CommandLineRunner {
	
	@Autowired
	RoomRepository roomRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Bootstrapping data...");
		
		roomRepository.save(new RoomEntity(101, "2000"));
		roomRepository.save(new RoomEntity(201, "3000"));
		roomRepository.save(new RoomEntity(301, "4000"));
		
		Iterable<RoomEntity> roomItr = roomRepository.findAll();
		
		System.out.println("Printing data...");
		for(RoomEntity room:roomItr) {
			System.out.println(room.getId());
		}
	}

}
