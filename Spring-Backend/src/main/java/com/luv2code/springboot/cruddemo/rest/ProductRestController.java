package com.luv2code.springboot.cruddemo.rest;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.luv2code.springboot.cruddemo.dao.ProductsRepository;
import com.luv2code.springboot.cruddemo.entity.Product;


@RestController
@RequestMapping("/api")
public class ProductRestController {

	private ProductsRepository productsRepository;
	
	@Autowired
	public ProductRestController(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}
	
	
	@GetMapping("/products")
	@PreAuthorize("hasRole('USER')")
	public List<Product> findAll() {
		return productsRepository.findAll();
	}

    @CrossOrigin(origins = "*")
	@PostMapping("/products")
	@PreAuthorize("hasRole('ADMIN')")
	public Product addProduct(@RequestBody Product product){
		
    
	return productsRepository.addProduct(product);
	}
    

	@GetMapping("/del-products/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Product deleteProduct(@PathVariable int id) {
		return productsRepository.deleteProduct(id);
	}

	@GetMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Product getProduct(@PathVariable int id) {
		System.out.println("product for id "+id);
		return productsRepository.getProduct(id);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/updateProducts")
	@PreAuthorize("hasRole('ADMIN')")
	public Product updateProduct(@RequestBody Product product){
		
    
	return productsRepository.updateProduct(product);
	}
	
}










