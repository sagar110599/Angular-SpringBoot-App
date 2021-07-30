import { Component, OnInit,Input  } from '@angular/core';
//import { checkServerIdentity } from 'tls';
import { ShoppingCart } from '../interfaces/ShoppingCart';
import { Product } from '../interfaces/product';
import { Observable, throwError } from 'rxjs';

import { CartService } from '../services/cart.service';

@Component({
  selector: 'product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {
  @Input() product:Product;
  @Input() cart:ShoppingCart;
  mycart:Observable<ShoppingCart>;
  count:any=0;
  present:boolean;
  constructor(private cartservice:CartService) { 
    
  }

  ngOnInit(): void {
    /* if(this.cart.order_product.findIndex(p=>p.product.id===this.product.id)>-1){
    this.present=true;
    console.log("Already in cart")
    } */
  }

  async addToCart(product:Product){
     this.mycart=await this.cartservice.addToCart(product)
     this.mycart.subscribe(
       response=>{
         this.cart=response;
         console.log("Updated Cart");
         console.log(this.cart);
         //this.ngOnInit();
       }
     );
   }
   
  
}
