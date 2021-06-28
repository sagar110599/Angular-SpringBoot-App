package com.luv2code.springboot.cruddemo.entity;
import com.luv2code.springboot.cruddemo.entity.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import java.util.List;
import com.luv2code.springboot.cruddemo.entity.Product;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name="Orders")
public class Order {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
   
   @JsonBackReference
   @ManyToOne
   @JoinColumn(name="cid")
   private User user;

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	


   
   @ManyToMany()  
   @JoinTable(name = "orders_product",   
            joinColumns = { @JoinColumn(name = "oid") },   
            inverseJoinColumns = { @JoinColumn(name = "pid") }) 
	private List<Product> products;	

	public List<Product> getProducts	() {
		return this.products	;
	}

	public void setProducts	(List<Product> products	) {
		this.products	 = products	;
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











