package com.tyss.centralizedlogging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.centralizedlogging.pojo.ProductDetails;
import com.tyss.centralizedlogging.repository.ProductDetailsRepository;

@Service
public class ProductService {
	
	@Autowired 
	private ProductDetailsRepository productDetailsRepository;
	
	public ProductDetails saveProduct(ProductDetails productDetails) {
		return productDetailsRepository.save(productDetails);
	}

}
