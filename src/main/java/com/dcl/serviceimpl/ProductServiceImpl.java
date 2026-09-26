package com.dcl.serviceimpl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcl.dto.ProductDto;
import com.dcl.entity.Product;
import com.dcl.exception.AppException;
import com.dcl.repo.ProductRepo;
import com.dcl.request.ProductRequest;
import com.dcl.request.UpdateRequest;
import com.dcl.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepo prepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public ProductDto addproduct(ProductRequest request) {
		/*
		 * Product p=new Product(); p.setProductName(request.getProductName());
		 * p.setBrand(request.getBrand()); p.setPrice(request.getPrice());
		 */

		Product p = mapper.map(request, Product.class);
		prepo.save(p);

		return mapper.map(p, ProductDto.class);
	}

	@Override
	public ProductDto getproductByid(Integer pid) {
		Product p=prepo.findById(pid).orElseThrow(() -> new AppException("Product not found", HttpStatus.NOT_FOUND));

		return mapper.map(p, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProduct() {
		

		List<ProductDto>listproduct= prepo.findAll()
		.stream()
		.map(p->mapper.map(p, ProductDto.class))
		.collect(Collectors.toList());



		return listproduct;
	}

	@Override
	public void delteproduct(Integer pid) {
		prepo.findById(pid).orElseThrow(() -> new RuntimeException("Product not found!"));
		prepo.deleteById(pid);

		
	}

	
	@Override
	public ProductDto updateproduct(Integer pid, UpdateRequest request) {
		Product allreadyExist = prepo.findById(pid).orElseThrow(() -> new RuntimeException("PrductNot found"));
		mapper.map(request, allreadyExist);

		Product update = prepo.save(allreadyExist);

		return mapper.map(update, ProductDto.class);
	}

}
