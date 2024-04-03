package com.example.user.service.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.user.service.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

	@PostMapping("/ratings")
	Rating createRating(Rating values);
	
	@GetMapping("/ratings/users/{userId}")
	List<Rating> getUserRatings(@PathVariable String userId);
	
}
