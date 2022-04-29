package com.tyss.mongodb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.mongodb.entity.SQLUserDetails;

@Repository
public interface SQLUserRepo extends JpaRepository<SQLUserDetails, Long>{

}
