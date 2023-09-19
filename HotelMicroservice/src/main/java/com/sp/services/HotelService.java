package com.sp.services;

import java.util.List;

import com.sp.entities.Hotel;

public interface HotelService {
	public Hotel saveHotel(Hotel Hotel);
	public List<Hotel> getAllHotels();
	public Hotel getHotelById(String HotelId);
	public Hotel updateHotel(Hotel Hotel,String HotelId);
	public Hotel deleteHotelById(String HotelId);
}
