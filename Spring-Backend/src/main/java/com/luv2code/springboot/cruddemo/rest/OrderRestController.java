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
import com.luv2code.springboot.cruddemo.dao.OrderRepository;
import com.luv2code.springboot.cruddemo.entity.Order;


@RestController
@RequestMapping("/api")
public class OrderRestController {

	private OrderRepository orderRepository;
	
	@Autowired
	public OrderRestController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	
	@GetMapping("/orders")
	@PreAuthorize("hasRole('USER')")
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

   /*  @CrossOrigin(origins = "*")
	@PostMapping("/orders")
	@PreAuthorize("hasRole('USER')")
	public Order create(@RequestBody Order order){
		
    
	return orderRepository.createOrder(order);
	} */
    

	@GetMapping("/del-orders/{id}")
	@PreAuthorize("hasRole('USER')")
	public Order delete(@PathVariable int id) {
		return orderRepository.deleteOrder(id);
	}

	@GetMapping("/orders/{userId}")
	@PreAuthorize("hasRole('USER')")
	public Order get(@PathVariable int userId) {
		System.out.println("Order for user id "+userId);
		return orderRepository.getOrder(userId);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/updateOrders")
	@PreAuthorize("hasRole('USER')")
	public Order update(@RequestBody Order order){
		
    
	return orderRepository.updateOrder(order);
	}
    @CrossOrigin(origins = "*")
	@GetMapping("/del-product-order/{oid}/{pid}")
	@PreAuthorize("hasRole('USER')")
	public Order deleteProduct(@PathVariable int oid,@PathVariable int pid){
		
    
	return orderRepository.deleteProductFromOrder(oid,pid);
	}

	
}










