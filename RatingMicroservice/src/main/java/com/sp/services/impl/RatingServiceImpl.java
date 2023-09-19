package com.sp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.entities.Rating;
import com.sp.repositories.RatingRepository;
import com.sp.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return this.ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return this.ratingRepository.findByHotelId(hotelId);
	}

	@Override
	public Rating updateRatingById(Rating rating, String ratingId) {
		Optional<Rating> r = this.ratingRepository.findById(ratingId);
		r.ifPresent(id -> {
			if(ratingId.equals(id.getRatingId())) {
				id.setFeedback(rating.getFeedback());
				id.setHotelId(rating.getHotelId());
				id.setRating(rating.getRating());
				id.setUserId(rating.getUserId());
			}
		});
		return this.ratingRepository.save(r.orElse(null));
	}
//
//	@Override
//	public Rating updateRatingByHotelId(Rating rating, String hotelId) {
//		return this.ratingRepository.updateRatingsByHotelId(rating,hotelId);
//	}
//
	@Override
	public Rating deleteRatingById(String ratingId) {
		Rating r = this.ratingRepository.findById(ratingId).get();
		this.ratingRepository.deleteById(ratingId);
		return r;
	}
//
//	@Override
//	public Rating deleteRatingByHotelId(String hotelId) {
//		return this.ratingRepository.deleteRatingByHotelId(hotelId);
//	}

	@Override
	public Rating saveRating(Rating rating) {
		return this.ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		return this.ratingRepository.findAll();
	}
	
}
