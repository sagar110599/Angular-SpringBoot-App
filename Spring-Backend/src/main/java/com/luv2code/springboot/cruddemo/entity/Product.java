package com.luv2code.springboot.cruddemo.entity;
import com.luv2code.springboot.cruddemo.entity.Order;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import java.util.*;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name="Product")
public class Product {

	// define fields
	
	
	private int id;
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pid")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	@Column(name="product_name")
	private String product_name;

	public String getProduct_name() {
		return this.product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	
	@Column(name="product_desc")
	private String product_desc;

	public String getProduct_desc() {
		return this.product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}


	

	
	@Column(name="price")
	private float price;

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}


	@Column(name="quantity")
	private int quantity;

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	
	private Set<OrderItem> order_product;
    @JsonBackReference
	@OneToMany(fetch = FetchType.EAGER,mappedBy="product",cascade = CascadeType.ALL)   
	public Set<OrderItem> getOrder_product() {
		return this.order_product;
	}

	public void setOrder_product(Set<OrderItem> order_product) {
		this.order_product = order_product;
	}
	
	@Column(name="product_image")
    private String product_image;

	public String getProduct_image() {
		return this.product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	// define constructors
	
	public Product() {
		
	}
    
	public Product(int id,String product_name,String product_desc,float price,int quantity,String product_image){
		this.id=id;
        this.product_name = product_name;
		this.product_desc=product_desc;
        this.price=price;
		this.quantity=quantity;
		this.product_image=product_image;
	}

	public Product(String product_name, String product_desc, float price,int quantity,String product_image) {
		this.product_name = product_name;
		this.product_desc= product_desc;
		this.quantity = quantity;
        this.price=price;
		this.product_image=product_image;
	}
    

	// define getter/setter
	
	

	@Override
	public String toString() {
		return "Product [id=" + id + ", Name=" + product_name+ ", Description=" + product_desc + ", qty=" + quantity + ", price="+price+" ]";
	}
		
}











