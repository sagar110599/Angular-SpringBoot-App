package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import com.luv2code.springboot.cruddemo.exception.myexceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Product;

@Repository
public class ProductsRepositoryImpl implements ProductsRepository {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public ProductsRepositoryImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	@Transactional
	public List<Product> findAll() throws DataNotFoundException{

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		TypedQuery<Product> theQuery =
				currentSession.createQuery("from Product p", Product.class);
		
		// execute query and get result list
		List<Product> products = theQuery.getResultList();
		
		//System.out.println("Last memmber is"+products.get(products.size()+2).getProduct_name());
		return products;
	}

	@Override
	@Transactional
	public Product addProduct(Product product) throws DataNotFoundException{

	Session currentSession = entityManager.unwrap(Session.class);
	currentSession.saveOrUpdate(product);
	return product;	
	}

	@Override
	@Transactional
	public Product deleteProduct(int id) throws DataNotFoundException{
    Session currentSession = entityManager.unwrap(Session.class);
	Product product=currentSession.get(Product.class,id);
	currentSession.delete(product);
	return product;	
	}

	@Override
	@Transactional
	public Product getProduct(int id){
    Session currentSession = entityManager.unwrap(Session.class);
	Product product=currentSession.get(Product.class,id);
	
	if(product==null){
		throw new DataNotFoundException("No Product With id "+id);
	}
	return product;	
	}

	@Override
	@Transactional
	public Product updateProduct(Product product) throws DataNotFoundException{

	Session currentSession = entityManager.unwrap(Session.class);
	currentSession.saveOrUpdate(product);
	return product;	
	}

	

}







