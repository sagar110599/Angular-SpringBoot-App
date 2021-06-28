package com.luv2code.springboot.cruddemo.rest;

import java.util.List;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import com.luv2code.springboot.cruddemo.dao.UserDAO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
    
	@CrossOrigin(origins = "*")
	@PostMapping("/login")
	public HashMap<String,String> checkLogin(@RequestBody HashMap body) {

		HashMap<String,String> result=new HashMap<>();
		String email=(String) body.get("email");
		String password=(String) body.get("password");
        if(UserDAO.checkLogin(email,password)){
        result.put("isLogin","true");
		}else{
			result.put("isLogin","false");
		}
		
		
		return result;
	}

	
}










