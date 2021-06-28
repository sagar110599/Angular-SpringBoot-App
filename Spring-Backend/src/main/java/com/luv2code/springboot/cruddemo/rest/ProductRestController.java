package com.luv2code.springboot.cruddemo.rest;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.luv2code.springboot.cruddemo.dao.ProductsDAO;
import com.luv2code.springboot.cruddemo.entity.Product;


@RestController
@RequestMapping("/api")
public class ProductRestController {

	private ProductsDAO ProductDAO;
	
	@Autowired
	public ProductRestController(ProductsDAO ProductDAO) {
		this.ProductDAO = ProductDAO;
	}
	
	
	@GetMapping("/products")
	public List<Product> findAll() {
		return ProductDAO.findAll();
	}

    @CrossOrigin(origins = "*")
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product){
		
    
	return ProductDAO.addProduct(product);
	}
    

	@GetMapping("/del-products/{id}")
	public Product deleteProduct(@PathVariable int id) {
		return ProductDAO.deleteProduct(id);
	}

	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable int id) {
		System.out.println("product for id "+id);
		return ProductDAO.getProduct(id);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/updateProducts")
	public Product updateProduct(@RequestBody Product product){
		
    
	return ProductDAO.updateProduct(product);
	}
	
}










