package com.tyss.mongodb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.mongodb.dto.UserDTO;
import com.tyss.mongodb.entity.UserDetails;
import com.tyss.mongodb.repo.UserRepo;

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
 
	public UserDTO updateUser(UserDTO user) {
		Optional<UserDetails> oldDetails = userRepo.findById(user.getId());
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

	public boolean deleteUser(String userId) {
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

	public UserDTO getUser(String userId) {
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
