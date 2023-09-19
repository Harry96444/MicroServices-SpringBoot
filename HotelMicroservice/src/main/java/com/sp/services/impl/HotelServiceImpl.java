package com.sp.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.entities.Hotel;
import com.sp.exceptions.ResourceNotFoundException;
import com.sp.repositories.HotelRepository;
import com.sp.services.HotelService;

import jakarta.persistence.Id;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository HotelRepository;

	@Override
	public Hotel saveHotel(Hotel Hotel) {
		String random = UUID.randomUUID().toString();
		Hotel.setId(random);
		return this.HotelRepository.save(Hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		return this.HotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(String HotelId) {
		return this.HotelRepository.findById(HotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id is not found on the server: "+HotelId));
	}

	@Override
	public Hotel updateHotel(Hotel Hotel, String HotelId) {
		Optional<Hotel> op = this.HotelRepository.findById(HotelId);
		op.ifPresent(id -> {
			if(HotelId.equals(id.getId())) {
				id.setName(Hotel.getName());
				id.setLocation(Hotel.getLocation());
				id.setAbout(Hotel.getAbout());
			}
		});
		return this.HotelRepository.save(op.orElse(null));
	}

	@Override
	public Hotel deleteHotelById(String HotelId) {
		Hotel u = this.HotelRepository.findById(HotelId).get();
		this.HotelRepository.delete(u);
		return u;
	}
	
}
