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
import com.luv2code.springboot.cruddemo.entity.PaymentStatus;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name="Transaction")
public class Transaction {

    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tid")
	private int id;
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="razor_pay_oid",nullable=true)
	private String razor_oid;

	public String getRazor_oid() {
		return this.razor_oid;
	}

	public void setRazor_oid(String razor_oid) {
		this.razor_oid = razor_oid;
	}

   @JsonBackReference
   @ManyToOne
   @JoinColumn(name="oid")	
   private Order order;
    
   
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order=order;
	}
    
	

    @Column(name="razor_pay_pid")
	private String razor_pid;

	public String getRazor_pid() {
		return this.razor_pid;
	}

	public void setRazor_pid(String razor_pid) {
		this.razor_pid = razor_pid;
	}

    @Column(name="razor_pay_sign")
    private String razor_sign;

	public String getRazor_sign() {
		return this.razor_sign;
	}

	public void setRazor_sign(String razor_sign) {
		this.razor_sign = razor_sign;
	}
    
	@Enumerated(EnumType.STRING)
    @Column(name="status")
    private PaymentStatus status;

	public PaymentStatus getStatus() {
		return this.status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	@Column(name="total")
	private String total;

	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}



    public Transaction(){

    }

@Override
	public String toString() {
		return "Transaction [oid= " + order.getId() + ", roid= "+razor_oid+" ,rpid="+razor_pid+" , sigin="+razor_sign+"]";
	}
		
}











