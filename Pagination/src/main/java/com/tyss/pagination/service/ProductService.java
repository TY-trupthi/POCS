package com.tyss.pagination.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tyss.pagination.pojo.ProductDetails;
import com.tyss.pagination.repo.ProductDetailsRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductDetailsRepository repo;

	public List<Page<ProductDetails>> getProducts() {
		 List<Page<ProductDetails>> pages = new ArrayList<Page<ProductDetails>>();
		List<ProductDetails> products = repo.findAll();
		int totalLoops = (int) Math.ceil(products.size()/2.0);		
		for (int i = 0; i < totalLoops; i++) {
			Pageable page = PageRequest.of(i, 2);
			pages.add(repo.findAll(page));
		}
        return pages;
	}
	
	public List<Page<ProductDetails>> getSortedProducts() {
		 List<Page<ProductDetails>> pages = new ArrayList<Page<ProductDetails>>();
		List<ProductDetails> products = repo.findAll();
		int totalLoops = (int) Math.ceil(products.size()/2.0);		
		for (int i = 0; i < totalLoops; i++) {
			Pageable page = PageRequest.of(i, 2, Sort.by(Sort.Direction.ASC, "productCode"));
			pages.add(repo.findAll(page));
		}
       return pages;
	}
	
	public int getCount() {
		return repo.GET_TOTAL_PRODUCT();
	}
}
