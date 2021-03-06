import { Component, OnInit,OnDestroy } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Product } from '../interfaces/product';
import { ShoppingCart } from '../interfaces/ShoppingCart';
import { AuthService } from '../services/auth.service';
import { CartService } from '../services/cart.service';
import { ProductServiceService } from '../services/product-service.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit , OnDestroy {
  products:Array<Product>=[]
  shoppingCart:ShoppingCart;
  cartSubscription:any;
  onlyOnce:boolean=true;
  constructor(private productservice:ProductServiceService,private cartservice:CartService,private authservice:AuthService) { 
    console.log("Inn constuctor")
  }

  async ngOnInit():Promise<void>{
    this.shoppingCart=await this.cartservice.getCart();
    console.log("Got my cart");
    this.productservice.getAll().subscribe(
      value=>{
        console.log("Now products are ready to be populated");
        this.products=value;
        
      }
    )
    this.cartSubscription=this.cartservice.getShoppingCart().subscribe(value=>{
    this.shoppingCart=value;
})
    
    
}

ngOnDestroy():void{
  console.log("Main Product page destroyed");
  this.cartSubscription.unsubscribe();
}


}
