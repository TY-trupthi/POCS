package com.example.springradis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.springradis.controller.pojo.UserDetails;
import com.example.springradis.dto.UserDTO;
import com.example.springradis.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public UserDTO saveUser(UserDTO user) {
		UserDetails userDetails = new UserDetails();
		BeanUtils.copyProperties(user, userDetails);
		userDetails = userRepo.save(userDetails);
		BeanUtils.copyProperties(userDetails, user);
		return user;
	}

	@CachePut(key = "#user.getUserId()", value = "User") 
	public UserDTO updateUser(UserDTO user) {
		Optional<UserDetails> oldDetails = userRepo.findById(user.getUserId());
		if (oldDetails.isPresent()) {
			UserDetails userDetails = new UserDetails();
			BeanUtils.copyProperties(user, userDetails);
			UserDetails modifiedDetails = userRepo.save(userDetails);
			BeanUtils.copyProperties(modifiedDetails, user);
			return user;
		} else {
			return null;
		}
	}

	@CacheEvict(key = "#userId", value = "User")
	public boolean deleteUser(Long userId) {
		if (userRepo.findById(userId).isPresent()) {
			userRepo.deleteById(userId);
			return true;
		} else
			return false;
	}

	public List<UserDTO> getAllUsers() {
		List<UserDetails> userDetailsList = userRepo.findAll();
		List<UserDTO> users = new ArrayList<>();
		for (UserDetails userDetails : userDetailsList) {
			UserDTO user = new UserDTO();
			BeanUtils.copyProperties(userDetails, user);
			users.add(user);
		}
		return users;
	}

	@Cacheable(key = "#userId", value = "User")
	public UserDTO getUser(Long userId) {
		Optional<UserDetails> user = userRepo.findById(userId);
		System.err.println(user.isPresent());
		UserDTO userDto = null;
		if (user.isPresent()) {
			System.err.println(user.get());
			userDto = new UserDTO();
			BeanUtils.copyProperties(user.get(), userDto);
		}
		return userDto;

	}

}
