package com.example.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.user.service.entities.Hotel;
import com.example.user.service.entities.Rating;
import com.example.user.service.entities.User;
import com.example.user.service.exceptions.ResourceNotFoundException;
import com.example.user.service.external.services.HotelService;
import com.example.user.service.external.services.RatingService;
import com.example.user.service.repositories.UserRepository;
import com.example.user.service.services.UserService;

import ch.qos.logback.classic.Logger;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	private Log logger = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	RatingService ratingService;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		User user =  userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Resource not found on server !!"+userId));
		
//		Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		
//		List<Rating>ratings = Arrays.stream(ratingOfUser).toList();
		List<Rating>ratings = ratingService.getUserRatings(userId);
		List<Rating>ratingHoteList = ratings.stream().map(rating -> {
//			ResponseEntity<Hotel>forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingHoteList);
		return user;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		 userRepository.deleteById(userId);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		//add his ratings - hw
		return userRepository.save(user);
	}
	
}
