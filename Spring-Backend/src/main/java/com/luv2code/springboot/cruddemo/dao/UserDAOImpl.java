package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public UserDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	@Transactional
	public List<User> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<User> theQuery =
				currentSession.createQuery("from User", User.class);
		
		// execute query and get result list
		List<User> Users = theQuery.getResultList();
		
		// return the results		
		return Users;
	}
    
	@Override
	@Transactional
	public boolean checkLogin(String email,String password) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<User> theQuery =
				currentSession.createQuery("from User u where u.email=:email AND u.password=:password", User.class);
		
		theQuery.setParameter("email",email);
		theQuery.setParameter("password",password);
		List<User> Users = theQuery.getResultList();
		if(Users.size()>0){
			System.out.println(Users.get(0));
			return true;
		}
		
		// return the results		
		return false;
	}

}







