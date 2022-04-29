package com.tyss.mongodb.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tyss.mongodb.entity.UserDetails;

public interface UserRepo extends MongoRepository<UserDetails, String>{

}
