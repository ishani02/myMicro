package com.example.hotel.services;

import java.util.List;

import com.example.hotel.entities.Hotel;

public interface HotelService {
	
	Hotel createHotel(Hotel hotel);
	List<Hotel>getAll();
	Hotel getHotel(String id);
}
