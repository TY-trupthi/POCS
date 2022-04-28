package com.tyss.centralizedlogging.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.centralizedlogging.dto.CommonResponse;
import com.tyss.centralizedlogging.pojo.ProductDetails;
import com.tyss.centralizedlogging.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(path = "/save")
	public ResponseEntity<CommonResponse> saveProduct(@RequestBody ProductDetails productDetails) {
		ProductDetails result=productService.saveProduct(productDetails);
		if(result!=null) {
			return new ResponseEntity<CommonResponse>(new CommonResponse("SUCCESS", false, new Date(), 200, result), HttpStatus.OK);
		}else {
			return new ResponseEntity<CommonResponse>(new CommonResponse("NOT FOUND", true, new Date(), 404, result), HttpStatus.NOT_FOUND);
		}
	}


}
