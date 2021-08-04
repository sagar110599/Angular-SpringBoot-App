import { Component, OnInit } from '@angular/core';
import { ShoppingCart } from '../interfaces/ShoppingCart';
import { Transaction } from '../interfaces/transaction';
import { CartService } from '../services/cart.service';
import { PaymentService } from '../services/payment.service';
import { environment } from '../../environments/environment';
declare let Razorpay: any;
@Component({
  selector: 'app-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.css']
})

export class CheckOutComponent implements OnInit {
shoppingCart:ShoppingCart;
transaction:Transaction;
key=environment.razorPayKey;
options:any;
  constructor(private cartservice:CartService,private paymentservice:PaymentService) { 
    
  }

  async ngOnInit(): Promise<void> {
    
    this.shoppingCart= await this.cartservice.getCart();
     this.transaction=await this.paymentservice.getRpayOrder({
    "userId":this.shoppingCart.user.id,   
    "orderId":this.shoppingCart.id,
    "amount":this.shoppingCart.getTotalCost()
    });
    console.log(this.transaction); 
    this.createOptions(this.transaction);
    
    
  
  }
  private createOptions(transaction:Transaction){
     this.options={
    "key": this.key, // Enter the Key ID generated from the Dashboard

    "amount": transaction.total, // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise

    "currency": "INR",

    "name": "Acme Corp",

    "description": "Test Transaction",

    "handler":this.paymentHandler.bind(this),

    "order_id": transaction.razor_oid, //This is a sample Order ID. Pass the `id` obtained in the response of Step 1

    

    "prefill": {

        "name": "Gaurav Kumar",

        "email": "gaurav.kumar@example.com",

        "contact": "9999999999"

    },

    "notes": {

        "address": "Razorpay Corporate Office"

    },

    "theme": {

        "color": "#3399cc"

    }

}

  }

  openRazorPay(){
    var razorpay = new Razorpay(this.options)
    razorpay.open();
  }
  private async paymentHandler(response:any){
    console.log(response);
    this.transaction.razor_pid=response.razorpay_payment_id;
    this.transaction.razor_sign=response.razorpay_signature;
    this.transaction.order=this.shoppingCart;
    console.log(this.transaction);
    var t=await this.paymentservice.checkPayment(this.transaction)
    console.log(t);
    
  }

}
