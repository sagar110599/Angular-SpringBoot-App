package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.User;

public interface UserDAO {

	public List<User> findAll();
		
}
