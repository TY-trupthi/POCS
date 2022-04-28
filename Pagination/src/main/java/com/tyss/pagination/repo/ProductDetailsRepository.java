package com.tyss.pagination.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.tyss.pagination.pojo.ProductDetails;


@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer>{
	
	@Procedure
	int GET_TOTAL_PRODUCT();

}
