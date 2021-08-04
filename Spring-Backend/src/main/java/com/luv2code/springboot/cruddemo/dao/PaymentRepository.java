package com.luv2code.springboot.cruddemo.dao;

import java.util.List;
import com.luv2code.springboot.cruddemo.rest.request.OrderRequest;
import com.luv2code.springboot.cruddemo.entity.Order;
import com.luv2code.springboot.cruddemo.entity.Transaction;

public interface PaymentRepository {

	
	public Transaction initaiteTransaction(OrderRequest orderRequest);
	public Transaction handleTransaction(Transaction ransaction);
	
		
}