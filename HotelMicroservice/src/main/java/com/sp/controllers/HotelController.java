package com.sp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.entities.Hotel;
import com.sp.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
	private HotelService HotelService;
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/readall")
	public ResponseEntity<List<Hotel>> getAllHotels() {
		return ResponseEntity.ok(this.HotelService.getAllHotels());
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/read/{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable("id") String HotelId) {
		Hotel u = this.HotelService.getHotelById(HotelId);
		return ResponseEntity.ok(u);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/create")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel Hotel) {
		Hotel u = this.HotelService.saveHotel(Hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Hotel> getHotelById(@RequestBody Hotel Hotel,@PathVariable("id") String HotelId) {
		Hotel u = this.HotelService.updateHotel(Hotel,HotelId);
		return ResponseEntity.ok(u);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Hotel> deleteHotelById(@PathVariable("id") String HotelId) {
		Hotel u = this.HotelService.deleteHotelById(HotelId);
		return ResponseEntity.ok(u);
	}
}
