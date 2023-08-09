package com.tyss.contactmanager.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.contactmanager.entity.ContactDetails;

@Repository
public interface ContactDetailsRepo extends JpaRepository<ContactDetails, Long>{
	
	Optional<ContactDetails> findByEmail(String email);

}
