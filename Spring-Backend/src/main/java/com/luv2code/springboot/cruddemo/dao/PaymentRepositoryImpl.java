package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.security.jwt.RazorPayUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import org.json.JSONObject;
import java.security.SignatureException;
import com.luv2code.springboot.cruddemo.rest.request.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Value;
import org.hibernate.Query;
import org.hibernate.Session;
import com.luv2code.springboot.cruddemo.exception.myexceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.luv2code.springboot.cruddemo.entity.Transaction;
import com.luv2code.springboot.cruddemo.entity.Order;
import com.luv2code.springboot.cruddemo.entity.PaymentStatus;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

	
	private String key;

	
	private String secret;
    
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	private EntityManager entityManager;


	private RazorpayClient client;	
	// set up constructor injection
	
	@Autowired
	public PaymentRepositoryImpl (@Value("${dama.app.rpayKey}") String key,@Value("${dama.app.rpaysecret}") String secret)throws RazorpayException {
		this.key=key;
		this.secret=secret;
		this.client = new RazorpayClient(key,secret);
        
	}
	
	
	@Override
	@Transactional
	public Transaction initaiteTransaction(OrderRequest orderRequest){
		
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query q=currentSession.createQuery("from Transaction t where t.order.id=:oid AND t.status=:status");
		q.setParameter("oid",orderRequest.getOrderId());
		q.setParameter("status",PaymentStatus.PENDING);
		//if no transction for given order with status pending create a new one and save
		if((q.list()).size()==0){
		try{	
			
		com.razorpay.Order razorPayorder=RazorPayUtils.createRazorPayOrder(toPaise(orderRequest.getAmount()),this.client);
		
		Transaction transaction=new Transaction();
		Order o=new Order();
		o.setId(orderRequest.getOrderId());
		transaction.setOrder(o);
		transaction.setRazor_oid((String)razorPayorder.get("id"));
		transaction.setTotal(toPaise(orderRequest.getAmount()));
		transaction.setStatus(PaymentStatus.PENDING);
		currentSession.saveOrUpdate(transaction);
        return transaction;
		}catch(RazorpayException r){
			throw new BadRequestException(r.getMessage());
		}
		}

		Transaction transaction=(Transaction) q.list().get(0);
		if(transaction.getTotal().equals(toPaise(orderRequest.getAmount())))
		return transaction;
		else{
			try{
			com.razorpay.Order razorPayorder=RazorPayUtils.createRazorPayOrder(orderRequest.getAmount(),this.client);
			transaction.setRazor_oid((String)razorPayorder.get("id"));
		    transaction.setTotal(toPaise(orderRequest.getAmount()));
			currentSession.saveOrUpdate(transaction);
		return transaction;
		}
		catch(RazorpayException r){
		throw new BadRequestException(r.getMessage());
		}
		}
	}

	@Override
	@Transactional
	public Transaction handleTransaction(Transaction transaction){

	Session currentSession = entityManager.unwrap(Session.class);
	
	
    try{
       String genSignature=RazorPayUtils.calculateRFC2104HMAC(transaction.getRazor_oid()+"|"+transaction.getRazor_pid(),secret);
	   System.out.println(genSignature);
	   if(genSignature.equals(transaction.getRazor_sign())){
		   RazorPayUtils.capturePayment(transaction.getRazor_pid(),transaction.getTotal(),this.client);
          transaction.setStatus(PaymentStatus.APPROVED);
		  transaction.getOrder().setFlag(true);
		  orderRepository.updateOrder(transaction.getOrder());

		  currentSession.saveOrUpdate(transaction); 
		  
	   }else{
		  transaction.setStatus(PaymentStatus.FAILED);
		  currentSession.saveOrUpdate(transaction); 
	   }

	}catch(Exception e){
		
      throw new BadRequestException("Failed Transaction!! Try Again");
	}
	

	
	return transaction;	
	}


	

private String toPaise(String paise) {
        BigDecimal b = new BigDecimal(paise);
        BigDecimal value = b.multiply(new BigDecimal("100"));
        return value.setScale(0, RoundingMode.UP).toString();
    }
	

}








