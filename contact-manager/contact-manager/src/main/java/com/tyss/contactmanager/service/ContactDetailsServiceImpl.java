package com.tyss.contactmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.tyss.contactmanager.dto.ContactDetailsDTO;
import com.tyss.contactmanager.entity.ContactDetails;
import com.tyss.contactmanager.repo.ContactDetailsRepo;
import com.tyss.contactmanager.util.CacheStore;

@Service
@Validated
public class ContactDetailsServiceImpl {

	@Autowired
	private ContactDetailsRepo contactDetailsRepo;

	@Autowired
	private CacheStore<ContactDetails> cacheStore;

	public List<ContactDetailsDTO> getAll() {
		return contactDetailsRepo.findAll().stream().map(details -> {
			cacheStore.add(details.getEmail(), details);
			return ContactDetailsDTO.builder().contactName(details.getContactName()).contactNumber(details.getContactNumber()).email(details.getEmail())
					.id(details.getId()).build();
		}).toList();
	}

	public ContactDetailsDTO getByEmail(String email) {
		ContactDetails contactDetails = cacheStore.get(email);
		if (contactDetails == null) {
			throw new RuntimeException("Not Present");
		}
		return ContactDetailsDTO.builder().contactName(contactDetails.getContactName()).email(contactDetails.getEmail())
				.id(contactDetails.getId()).build();
	}

	// @Transactional
	public ContactDetailsDTO save(ContactDetailsDTO contactDetailsDTO) {
		if (contactDetailsRepo.findByEmail(contactDetailsDTO.getEmail()).isPresent()) {
			throw new RuntimeException("Already Exist");
		}
		ContactDetails save = contactDetailsRepo.save(ContactDetails.builder()
				.contactName(contactDetailsDTO.getContactName()).email(contactDetailsDTO.getEmail())
				.contactNumber(contactDetailsDTO.getContactNumber()).build());
		cacheStore.add(save.getEmail(), save);
		return contactDetailsDTO;
	}

	public Boolean deleteByEmail(String email) {
		Optional<ContactDetails> contactDetailsOptional = contactDetailsRepo.findByEmail(email);
		if (!contactDetailsOptional.isPresent()) {
			throw new RuntimeException("Not Present");
		}
		contactDetailsRepo.delete(contactDetailsOptional.get());
		return true;
	}

	@Transactional
	public ContactDetailsDTO updateByEmail(String email, ContactDetailsDTO contactDetailsDTO) {
		Optional<ContactDetails> contactDetailsOptional = contactDetailsRepo.findByEmail(email);
		if (!contactDetailsOptional.isPresent()) {
			throw new RuntimeException("Not Present");
		}
		ContactDetails contactDetails = contactDetailsOptional.get();
		contactDetails.setContactName(contactDetailsDTO.getContactName());
		contactDetails.setEmail(contactDetailsDTO.getEmail());
		ContactDetails save = contactDetailsRepo.save(contactDetails);
		cacheStore.add(save.getEmail(), save);
		return contactDetailsDTO;
	}

}
