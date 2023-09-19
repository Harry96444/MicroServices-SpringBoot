package com.sp.services;

import java.util.List;

import com.sp.entities.Rating;

public interface RatingService {
	public Rating saveRating(Rating Rating);
	public List<Rating> getAllRatings();
	public List<Rating> getRatingByUserId(String userId);
	public List<Rating> getRatingByHotelId(String hotelId);
	public Rating updateRatingById(Rating rating,String userId);
//	public Rating updateRatingByHotelId(Rating rating,String hotelId);
	public Rating deleteRatingById(String userId);
//	public Rating deleteRatingByHotelId(String hotelId);
}
