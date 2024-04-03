package com.example.rating.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rating.entities.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String>{
	
	List<Rating>findByUserId(String userId);
	List<Rating>findByHotelId(String hotelId);
}
