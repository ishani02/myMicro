package com.example.user.service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.user.service.entities.User;


public interface UserService {

	//create user
	public User saveUser(User user);
	
	//getUsers
	public List<User> getAllUsers();
	
	//get single user
	public User getUser(String userId);
	
	//delete user
	public void deleteUser(String userId);
	
	//update user
	public User updateUser(User user);
	
}
