package com.sp.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sp.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {
	
	public List<Rating> findByUserId(String userId);
	public List<Rating> findByHotelId(String hotelId);
//	public Rating updateRatingsById(Rating rating, String ratingId);
//	public Rating updateRatingsByHotelId(Rating rating,String hotelId);
//	public Rating deleteRatingByUserId(String userId);
//	public Rating deleteRatingByHotelId(String hotelId);
}
