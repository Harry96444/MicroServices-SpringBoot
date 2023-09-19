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

import com.sp.entities.Rating;
import com.sp.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired
	private RatingService RatingService;
	
	@GetMapping("/readall")
	public ResponseEntity<List<Rating>> getAllRatings() {
		return ResponseEntity.ok(this.RatingService.getAllRatings());
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/readbyuser/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
		List<Rating> u = this.RatingService.getRatingByUserId(userId);
		return ResponseEntity.ok(u);
	}
	
	@GetMapping("/readbyhotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
		List<Rating> u = this.RatingService.getRatingByHotelId(hotelId);
		return ResponseEntity.ok(u);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/create")
	public ResponseEntity<Rating> createRating(@RequestBody Rating Rating) {
		Rating r = this.RatingService.saveRating(Rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(r);
	}
	
	@PutMapping("/update/{ratingId}")
	public ResponseEntity<Rating> updateRatingByUserId(@RequestBody Rating Rating,@PathVariable String ratingId) {
		Rating u = this.RatingService.updateRatingById(Rating,ratingId);
		return ResponseEntity.ok(u);
	}
//	
//	@PutMapping("/updatebyhotel/{hotelId}")
//	public ResponseEntity<Rating> getRatingById(@RequestBody Rating Rating,@PathVariable String hotelId) {
//		Rating u = this.RatingService.updateRatingByHotelId(Rating,hotelId);
//		return ResponseEntity.ok(u);
//	}
//	
	@DeleteMapping("/delete/{ratingId}")
	public ResponseEntity<Rating> deleteRatingByUserId(@PathVariable String ratingId) {
		Rating u = this.RatingService.deleteRatingById(ratingId);
		return ResponseEntity.ok(u);
	}
//	
//	@DeleteMapping("/delete/{hotelId}")
//	public ResponseEntity<Rating> deleteRatingByHotelId(@PathVariable String hotelId) {
//		Rating u = this.RatingService.deleteRatingByHotelId(hotelId);
//		return ResponseEntity.ok(u);
//	}
}
