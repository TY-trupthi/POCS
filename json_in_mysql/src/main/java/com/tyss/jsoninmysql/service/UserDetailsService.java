package com.tyss.jsoninmysql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.jsoninmysql.dto.UserDTO;
import com.tyss.jsoninmysql.entity.UserDetails;
import com.tyss.jsoninmysql.repo.UserRepo;

@Service
public class UserDetailsService {
	
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

	public UserDTO getUser(int userId) {
		Optional<UserDetails> user = userRepo.findById(userId);
		UserDTO userDto = null;
		if (user.isPresent()) {
			userDto = new UserDTO();
			BeanUtils.copyProperties(user.get(), userDto);
		}
		return userDto;

	}


}
