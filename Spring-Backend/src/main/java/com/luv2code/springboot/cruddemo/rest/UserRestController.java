package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.dao.UserDAO;
import com.luv2code.springboot.cruddemo.entity.User;

@RestController
@RequestMapping("/api")
public class UserRestController {

	private UserDAO UserDAO;
	
	@Autowired
	public UserRestController(UserDAO theUserDAO) {
		UserDAO = theUserDAO;
	}
	
	// expose "/Users" and return list of Users
	@GetMapping("/users")
	public List<User> findAll() {
		return UserDAO.findAll();
	}

	
}










