package com.sp.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.sp.entities.Hotel;
import com.sp.entities.Rating;
import com.sp.entities.User;
import com.sp.exceptions.ResourceNotFoundException;
import com.sp.externalservices.HotelService;
import com.sp.externalservices.RatingService;
import com.sp.repositories.UserRepository;
import com.sp.services.UserService;

import jakarta.persistence.Id;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
//	@Autowired 
//	private RatingService ratingService;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User saveUser(User user) {
		String random = UUID.randomUUID().toString();
		user.setUserId(random);
		return this.userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		// @GetMapping("/readall")
		// http://localhost:8977/ratings/readall
//		User u = new User();
//		ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8977/ratings/readall", ArrayList.class);
//		logger.info("{}",ratingsOfUser);
//		ratingsOfUser.get(0).getUserId();
//		u.setRatings(ratingsOfUser);
		return this.userRepository.findAll();
	}

// By RestTemplate :
//	@Override
//	public User getUserById(String userId) {
//		// Intercommunication between API's
//		// Get hotel ratings of the user
//		// @GetMapping("/readbyuser/{userId}")
//		// http://localhost:8977/ratings/readbyuser/46253e96-c7c8-490b-9cf8-726c74273105
//		User u = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on the server: "+userId));
//		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/readbyuser/"+u.getUserId(), Rating[].class);
//		logger.info("{} ",ratingsOfUser);
//		
//		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
//		// @GetMapping("/read/{id}")
//		// Get Hotel also for particular rating
//		// http://localhost:8978/hotels/read/1e43513e-e692-477f-a89b-a3cf69ddbe6e
//		List<Rating> ratingList = ratings.stream().map(rating -> {
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/read/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
//			logger.info("response entity code {}",forEntity.getBody());
//			rating.setHotel(hotel);
//			return rating;
//		}).collect(Collectors.toList());
//		u.setRatings(ratingList);
//		return u;
//	}
	
	
	// By Feign Client
	@Override
	public User getUserById(String userId) {
		// Intercommunication between API's
		// Get hotel ratings of the user
		// @GetMapping("/readbyuser/{userId}")
		// http://localhost:8977/ratings/readbyuser/46253e96-c7c8-490b-9cf8-726c74273105
		User u = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on the server: "+userId));
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/readbyuser/"+u.getUserId(), Rating[].class);
		logger.info("{} ",ratingsOfUser);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		// @GetMapping("/read/{id}")
		// Get Hotel also for particular rating
		// http://localhost:8978/hotels/read/1e43513e-e692-477f-a89b-a3cf69ddbe6e
		List<Rating> ratingList = ratings.stream().map(rating -> {
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		u.setRatings(ratingList);
		return u;
	}

	@Override
	public User updateUser(User user, String userId) {
		Optional<User> op = this.userRepository.findById(userId);
		op.ifPresent(id -> {
			if(userId.equals(id.getUserId())) {
				id.setName(user.getName());
				id.setEmail(user.getEmail());
				id.setAbout(user.getAbout());
			}
		});
		return this.userRepository.save(op.orElse(null));
	}

	@Override
	public User deleteUserById(String userId) {
		User u = this.userRepository.findById(userId).get();
		this.userRepository.delete(u);
		return u;
	}
	
}
