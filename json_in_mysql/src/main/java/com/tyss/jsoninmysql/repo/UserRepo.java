package com.tyss.jsoninmysql.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.jsoninmysql.entity.UserDetails;

@Repository
public interface UserRepo extends JpaRepository<UserDetails, Integer>{

}
