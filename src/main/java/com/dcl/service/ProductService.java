package com.dcl.service;

import java.util.List;

import com.dcl.dto.ProductDto;
import com.dcl.entity.Product;
import com.dcl.request.ProductRequest;
import com.dcl.request.UpdateRequest;

public interface ProductService {
	ProductDto addproduct(ProductRequest request);
	
	ProductDto getproductByid(Integer pid);
	
	List<ProductDto>getAllProduct();
	
	ProductDto updateproduct(Integer pid,UpdateRequest request);
	
	void delteproduct(Integer pid);

}  


