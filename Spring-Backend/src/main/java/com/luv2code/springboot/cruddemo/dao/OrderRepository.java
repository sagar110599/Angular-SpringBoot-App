package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Order;

public interface OrderRepository {

	public List<Order> findAll();
	public Order createOrder(Order order);
	public Order deleteOrder(int id);
	public Order getOrder(int userId);
	public Order updateOrder(Order order);
		
}