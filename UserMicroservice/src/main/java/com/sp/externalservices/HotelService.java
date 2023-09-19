package com.sp.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sp.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
	
	@GetMapping("/hotels/read/{hotelId}")
	public Hotel getHotel(@PathVariable String hotelId);
	
	
}
