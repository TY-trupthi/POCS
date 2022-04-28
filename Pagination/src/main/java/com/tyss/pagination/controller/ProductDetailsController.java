package com.tyss.pagination.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.pagination.service.ProductService;
import com.tyss.pagination.veiw.SuccessResponse;


@RestController
@RequestMapping(path = "/page")
public class ProductDetailsController {
	
	@Autowired
	private ProductService productService;

	@GetMapping(path = "/get")
	public ResponseEntity<SuccessResponse> getProducts() {
		return new ResponseEntity<SuccessResponse>(new SuccessResponse(productService.getProducts(), true),
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/get/sorted/products")
	public ResponseEntity<SuccessResponse> getSortedProducts() {
		return new ResponseEntity<SuccessResponse>(new SuccessResponse(productService.getSortedProducts(), true),
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/get/count")
	public ResponseEntity<SuccessResponse> getCount(){
		return new ResponseEntity<SuccessResponse>(new SuccessResponse(productService.getCount(), true),
				HttpStatus.OK);
	}

}
