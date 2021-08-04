import { Component, OnInit,Input,OnChanges,SimpleChanges } from '@angular/core';
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
  
  present:boolean=false;
  constructor(private cartservice:CartService) { 
    
  }

  ngOnInit(): void {
    
      if(this.cart.getProductIndex(this.product)>-1){
    this.present=true;
    }else{
      this.present=false;
    }  
    
  }
  

  async addToCart(product:Product){
    this.cart.addProduct(product);
    await this.cartservice.updateShoppingCart(this.cart);
    console.log("After Update");
    console.log(this.cart);
    this.ngOnInit();
     
   }
   async removeFromCart(product:Product){
     this.cart.removeProductFromCart(product);
     await this.cartservice.deleteProductFromCart(product,this.cart.id);
     console.log("After Delete");
    console.log(this.cart);
    this.ngOnInit();
   }
   
  
  
}
