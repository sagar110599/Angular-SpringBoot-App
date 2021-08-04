package com.luv2code.springboot.cruddemo.dao;

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
		System.out.println(key+"   "+secret);
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
			
		
			
		com.razorpay.Order razorPayorder=createRazorPayOrder(orderRequest.getAmount());
		
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
			System.out.println(r.getMessage());
			System.out.println(r);
		throw new DataNotFoundException("Something Went wrong in creating order");
		}
		}
		Transaction transaction=(Transaction) q.list().get(0);
		if(transaction.getTotal().equals(toPaise(orderRequest.getAmount())))
		return transaction;
		else{
			try{
			com.razorpay.Order razorPayorder=createRazorPayOrder(orderRequest.getAmount());
			transaction.setRazor_oid((String)razorPayorder.get("id"));
		    transaction.setTotal(toPaise(orderRequest.getAmount()));
			currentSession.saveOrUpdate(transaction);
		return transaction;}
		catch(RazorpayException r){
		throw new DataNotFoundException("Something Went wrong in creating order");
		}
		}
	}

	@Override
	@Transactional
	public Transaction handleTransaction(Transaction transaction){

	Session currentSession = entityManager.unwrap(Session.class);
	
	
    try{
       String genSignature=Signature.calculateRFC2104HMAC(transaction.getRazor_oid()+"|"+transaction.getRazor_pid(),secret);
	   System.out.println(genSignature);
	   if(genSignature.equals(transaction.getRazor_sign())){
          transaction.setStatus(PaymentStatus.APPROVED);
		  transaction.getOrder().setFlag(true);
		  orderRepository.updateOrder(transaction.getOrder());
		  currentSession.saveOrUpdate(transaction); 
		  
	   }else{
		  transaction.setStatus(PaymentStatus.FAILED);
		  currentSession.saveOrUpdate(transaction); 
	   }

	}catch(Exception e){
		System.out.println(e);
		System.out.println(e.getMessage());
      throw new DataNotFoundException("Failed Transaction!! Try Again");
	}
	

	
	return transaction;	
	}


	
private com.razorpay.Order createRazorPayOrder(String amount) throws RazorpayException{

JSONObject options=new JSONObject();
String amountInPaise=toPaise(amount);
options.put("amount",amountInPaise);
options.put("currency","INR");
return this.client.Orders.create(options);
}
private String toPaise(String paise) {
        BigDecimal b = new BigDecimal(paise);
        BigDecimal value = b.multiply(new BigDecimal("100"));
        return value.setScale(0, RoundingMode.UP).toString();
    }
	
private static class Signature {
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    
    public static String calculateRFC2104HMAC(String data, String mysecret) throws java.security.SignatureException {
        String result;
        try {
 
            // get an hmac_sha256 key from the raw secret bytes
			System.out.println("Inside calculate");
			System.out.println(data+"       "+mysecret);
            SecretKeySpec signingKey = new SecretKeySpec(mysecret.getBytes(), HMAC_SHA256_ALGORITHM);
           System.out.println(signingKey);
            // get an hmac_sha256 Mac instance and initialize with the signing
            // key
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
 
            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes());
 
            // base64-encode the hmac
            result = DatatypeConverter.printHexBinary(rawHmac).toLowerCase();
       System.out.println(result);
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }
}
}







