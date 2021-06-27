package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import com.luv2code.springboot.cruddemo.exception.MyGeneralExe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Product;

@Repository
public class ProductDAOImpl implements ProductsDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public ProductDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	@Transactional
	public List<Product> findAll() throws MyGeneralExe{

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		TypedQuery<Product> theQuery =
				currentSession.createQuery("select new Product(p.id,p.product_name,p.product_desc,p.price) from Product p", Product.class);
		
		// execute query and get result list
		List<Product> products = theQuery.getResultList();
		
		//System.out.println("Last memmber is"+products.get(products.size()+2).getProduct_name());
		return products;
	}

}







