package com.sp.externalservices;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.common.base.Objects;
import com.sp.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@PostMapping("/ratings/create")
	public Rating createRating(Rating values);
	@PutMapping("/ratings/update/{ratingId}")
	public Rating updateRating(@RequestBody Rating rating,String ratingId);
	@DeleteMapping("ratings/delete/{ratingId}")
	public Rating deleteRating(String ratingId);
}
