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
import java.util.List;
import com.luv2code.springboot.cruddemo.entity.*;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name="Orders_product")

public class OrderItem {

private int id;

	


@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="opid")
public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
private Product product;
    
	
    @ManyToOne
	@JoinColumn(name = "pid") 
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}



private Order order;
    @JsonBackReference
	@ManyToOne
	@JoinColumn(name = "oid") 
    public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

@Column(name="quantity")
private int quantity;

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	




	
		
}











