package com.sp.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.entities.User;
import com.sp.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@GetMapping("/readall")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
//  Circuit Breaker	
//	@GetMapping("/read/{id}")
//	@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
//	public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
//		User u = this.userService.getUserById(userId);
//		return ResponseEntity.ok(u);
//	}
//	
//	// creating Fallback method for circuitBreaker
//	public ResponseEntity<User> ratingHotelFallBack(String userId,Exception e) {
//		logger.info("Fallback is executed because service is down",e.getMessage());
//		User u = User.builder().email("dummy@email.com").name("dummy").about("This is dummy user because service is down").userId("6766").build();
//		return new ResponseEntity<>(u,HttpStatus.OK);
//	}
	
//  Retry Module
//	int retryCount = 1;
//	@GetMapping("/read/{id}")
//	@Retry(name="ratingHotelRetry",fallbackMethod = "ratingHotelFallBack")
//	public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
//		retryCount++;
//		logger.info("Retry Count: {} ",retryCount);
//		User u = this.userService.getUserById(userId);
//		return ResponseEntity.ok(u);
//	}
//	
//	// creating Fallback method for circuitBreaker
//	public ResponseEntity<User> ratingHotelFallBack(String userId,Exception e) {
//		logger.info("Fallback is executed because service is down",e.getMessage());
//		User u = User.builder().email("dummy@email.com").name("dummy").about("This is dummy user because service is down").userId("6766").build();
//		return new ResponseEntity<>(u,HttpStatus.OK);
//	}

//  Rate Limiter
	int retryCount = 1;
	@GetMapping("/read/{id}")
	@RateLimiter(name="ratingHotelLimiter",fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
		retryCount++;
		logger.info("Retry Count: {} ",retryCount);
		User u = this.userService.getUserById(userId);
		return ResponseEntity.ok(u);
	}
	
	// creating Fallback method for circuitBreaker
	public ResponseEntity<User> ratingHotelFallBack(String userId,Exception e) {
		logger.info("Fallback is executed because service is down",e.getMessage());
		User u = User.builder().email("dummy@email.com").name("dummy").about("This is dummy user because service is down").userId("6766").build();
		return new ResponseEntity<>(u,HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User u = this.userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> getUserById(@RequestBody User user,@PathVariable("id") String userId) {
		User u = this.userService.updateUser(user,userId);
		return ResponseEntity.ok(u);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable("id") String userId) {
		User u = this.userService.deleteUserById(userId);
		return ResponseEntity.ok(u);
	}
}
