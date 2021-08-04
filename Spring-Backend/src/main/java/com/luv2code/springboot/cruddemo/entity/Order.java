package com.luv2code.springboot.cruddemo.entity;
import com.luv2code.springboot.cruddemo.entity.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import java.util.*;
import com.luv2code.springboot.cruddemo.entity.Product;
import com.luv2code.springboot.cruddemo.entity.Transaction;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name="Orders")
public class Order {

	// define fields
	
	
	private int id;
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="oid")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
   
   
   private User user;
    
	@JsonBackReference
   @ManyToOne
   @JoinColumn(name="cid")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="flag")
	private boolean flag;

	public boolean isFlag() {
		return this.flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

    private Set<Transaction> transactions;

	

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER,mappedBy="order")
     public Set<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

    
    
   
   
   private Set<OrderItem>order_product;	
    @JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER,mappedBy="order",cascade = CascadeType.ALL)
	public Set<OrderItem> getOrder_product() {
		return this.order_product;
	}

	public void setOrder_product(Set<OrderItem> order_product) {
		this.order_product = order_product;
	}
	

	
	
	
	
	
		
	// define constructors
	
	public Order() {
		
	}

	

	// define getter/setter
	
	

	@Override
	public String toString() {
		return "Order [id= " + id + ", cid= "+user.getId()+" ]";
	}
		
}











