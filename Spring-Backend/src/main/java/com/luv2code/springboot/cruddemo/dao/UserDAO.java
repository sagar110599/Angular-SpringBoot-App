package com.luv2code.springboot.cruddemo.dao;

import java.util.List;
import java.util.HashMap;

import com.luv2code.springboot.cruddemo.entity.User;

public interface UserDAO {

	public List<User> findAll();
	public boolean checkLogin(String email,String password);
		
}
