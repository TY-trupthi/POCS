package com.example.springradis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springradis.controller.pojo.UserDetails;

@Repository
public interface UserRepo extends JpaRepository<UserDetails, Long>{

}
