package com.luv2code.springboot.cruddemo.dao;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.*;
import com.luv2code.springboot.cruddemo.exception.myexceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Order;
import com.luv2code.springboot.cruddemo.entity.*;
import com.luv2code.springboot.cruddemo.entity.User;
import com.luv2code.springboot.cruddemo.entity.Product;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public OrderRepositoryImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	@Transactional
	public List<Order> findAll() throws DataNotFoundException{

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		TypedQuery<Order> theQuery =
				currentSession.createQuery("from Order o", Order.class);
		
		// execute query and get result list
		List<Order> orders = theQuery.getResultList();
		
		//System.out.println("Last memmber is"+products.get(products.size()+2).getProduct_name());
		return orders;
	}

	@Override
	@Transactional
	public Order createOrder(Order order) throws DataNotFoundException{

	Session currentSession = entityManager.unwrap(Session.class);
	currentSession.saveOrUpdate(order);
	return order;	
	}

	@Override
	@Transactional
	public Order deleteOrder(int id) throws DataNotFoundException{
    Session currentSession = entityManager.unwrap(Session.class);
	Order order=currentSession.get(Order.class,id);
    if(order==null){
	throw new DataNotFoundException("No Order With id "+id);
    }
    currentSession.delete(order);
    System.out.println(order);
	return order;

	}

	@Override
	@Transactional
	public Order getOrder(int userId){
    Session currentSession = entityManager.unwrap(Session.class);
	Query q=currentSession.createQuery("from Order o where o.flag=:flag AND o.user.id=:user_id");
    q.setParameter("flag",false);
    q.setParameter("user_id",userId);
    
	
	if((q.list()).size()==0){
		User user=currentSession.get(User.class,userId);
		if(user==null){
		    throw new DataNotFoundException("User With Id "+userId+" does not exist");
		}
		Order o=new Order();
		o.setUser(user);
		o.setOrder_product(new HashSet<OrderItem>());
		return createOrder(o);
	}
    Order order=(Order)(q.list()).get(0);
	return order;	
	}

	@Override
	@Transactional
	public Order updateOrder(Order order) throws DataNotFoundException{

	Session currentSession = entityManager.unwrap(Session.class);
	currentSession.saveOrUpdate(order);
	return order;	
	}

	@Override
	@Transactional
	public Order deleteProductFromOrder(int oid,int pid) throws DataNotFoundException{

	Session currentSession = entityManager.unwrap(Session.class);
	
	
		javax.persistence.Query query = entityManager.createQuery("delete from OrderItem o where o.order.id= :oid AND o.product.id= :pid");
			
		query.setParameter("oid", oid);
		query.setParameter("pid", pid);
		int result =query.executeUpdate();
		
	Order order=currentSession.get(Order.class,oid);
	return order;	
	}

	

}







