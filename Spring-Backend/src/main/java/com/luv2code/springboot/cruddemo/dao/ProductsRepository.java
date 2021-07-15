package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Product;

public interface ProductsRepository {

	public List<Product> findAll();
	public Product addProduct(Product product);
	public Product deleteProduct(int id);
	public Product getProduct(int id);
	public Product updateProduct(Product product);
		
}
