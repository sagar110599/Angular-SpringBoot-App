package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Product;

public interface ProductsDAO {

	public List<Product> findAll();
	public Product addProduct(Product product);
		
}
