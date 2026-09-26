package com.dcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.dto.ProductDto;
import com.dcl.request.ProductRequest;
import com.dcl.request.UpdateRequest;
import com.dcl.response.ApiResponse;
import com.dcl.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController { 
	@Autowired
	private ProductService pservice;
	
	@PostMapping("/add")
	
	public ResponseEntity<?>Addproduct( @RequestBody ProductRequest request){
		ProductDto dto=pservice.addproduct(request);
		
		
		return ResponseEntity.ok(new ApiResponse<>("product Added sucessfully",dto,HttpStatus.OK));
	}
	@GetMapping("/get/{pid}")
	public ResponseEntity<?>getbyid( @PathVariable Integer   pid){
		ProductDto pdto=pservice.getproductByid(pid);
		return ResponseEntity.ok(new ApiResponse("productinfo:", pdto, HttpStatus.OK));
	}
	  
	@GetMapping("/getall")
	public ResponseEntity<?>getall(){
		List<ProductDto> plist=pservice.getAllProduct();
		return ResponseEntity.ok(new ApiResponse<>("all products", plist, HttpStatus.OK));
	}
	@DeleteMapping("/delete{pid}")
	public ResponseEntity<?>delete( @PathVariable Integer pid){
		pservice.delteproduct(pid);
		return ResponseEntity.ok("delete sucessfully");
		
	}
	@PutMapping("/update{pid}")
	public ResponseEntity<?>update(@PathVariable Integer pid,@RequestBody UpdateRequest request){
		ProductDto pdto=pservice.updateproduct(pid, request);
		return ResponseEntity.ok(new ApiResponse<>("product updated successfully", pdto,HttpStatus.OK));
	}

}
