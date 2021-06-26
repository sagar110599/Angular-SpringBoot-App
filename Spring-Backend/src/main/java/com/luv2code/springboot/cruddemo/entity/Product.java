package com.luv2code.springboot.cruddemo.entity;
import com.luv2code.springboot.cruddemo.entity.Order;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import java.util.List;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name="Product")
public class Product {

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

	@JsonBackReference
	@ManyToMany()  
    @JoinTable(name = "orders_product",   
            joinColumns = { @JoinColumn(name = "pid") },   
            inverseJoinColumns = { @JoinColumn(name = "oid") })  
	private List<Order> orders;

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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
    
	public Product(String product_name,float price,int quantity){
        this.product_name = product_name;
		this.quantity = quantity;
        this.price=price;
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











