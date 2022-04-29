package com.tyss.mongodb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tyss.mongodb.dto.SQLUserDTO;
import com.tyss.mongodb.entity.SQLUserDetails;
import com.tyss.mongodb.repo.SQLUserRepo;


@Service
public class SQLUserService {
	
	@Autowired
	private SQLUserRepo userRepo;
	
	public SQLUserDTO saveUser(SQLUserDTO user) {
		SQLUserDetails userDetails = new SQLUserDetails();
		BeanUtils.copyProperties(user, userDetails);
		userDetails = userRepo.save(userDetails);
		BeanUtils.copyProperties(userDetails, user);
		return user;
	}

	@CachePut(key = "#user.getId()", value = "SQLUser") 
	public SQLUserDTO updateUser(SQLUserDTO user) {
		Optional<SQLUserDetails> oldDetails = userRepo.findById(user.getId());
		if (oldDetails.isPresent()) {
			SQLUserDetails userDetails = new SQLUserDetails();
			BeanUtils.copyProperties(user, userDetails);
			SQLUserDetails modifiedDetails = userRepo.save(userDetails);
			BeanUtils.copyProperties(modifiedDetails, user);
			return user;
		} else {
			return null;
		}
	}

	@CacheEvict(key = "#userId", value = "SQLUser")
	public boolean deleteUser(Long userId) {
		if (userRepo.findById(userId).isPresent()) {
			userRepo.deleteById(userId);
			return true;
		} else
			return false;
	}

	public List<SQLUserDTO> getAllUsers() {
		List<SQLUserDetails> userDetailsList = userRepo.findAll();
		List<SQLUserDTO> users = new ArrayList<>();
		for (SQLUserDetails userDetails : userDetailsList) {
			SQLUserDTO user = new SQLUserDTO();
			BeanUtils.copyProperties(userDetails, user);
			users.add(user);
		}
		return users;
	}

	@Cacheable(key = "#userId", value = "SQLUser")
	public SQLUserDTO getUser(Long userId) {
		Optional<SQLUserDetails> user = userRepo.findById(userId);
		System.err.println(user.isPresent());
		SQLUserDTO userDto = null;
		if (user.isPresent()) {
			System.err.println(user.get());
			userDto = new SQLUserDTO();
			BeanUtils.copyProperties(user.get(), userDto);
		}
		return userDto;

	}


}
