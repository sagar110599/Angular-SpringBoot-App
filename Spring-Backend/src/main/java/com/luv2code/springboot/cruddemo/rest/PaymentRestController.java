package com.luv2code.springboot.cruddemo.rest;

import java.util.*;
import com.luv2code.springboot.cruddemo.rest.request.OrderRequest;
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
import com.luv2code.springboot.cruddemo.dao.PaymentRepository;
import com.luv2code.springboot.cruddemo.entity.Transaction;


@RestController
@RequestMapping("/api/payment")
public class PaymentRestController {

	private PaymentRepository paymentRepository;
	
	@Autowired
	public PaymentRestController(PaymentRepository paymentRepository) {
		this.paymentRepository=paymentRepository;
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/start")
	@PreAuthorize("hasRole('USER')")
	public Transaction startTransaction(@RequestBody OrderRequest orderRequest){
		
    return paymentRepository.initaiteTransaction(orderRequest);
	}
    @CrossOrigin(origins = "*")
	@PostMapping("/verify")
	@PreAuthorize("hasRole('USER')")
	public Transaction verifyTransaction(@RequestBody Transaction transaction){
		
    
	return paymentRepository.handleTransaction(transaction);
	}

	
}










