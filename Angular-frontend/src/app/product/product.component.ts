import { Component, OnInit } from '@angular/core';
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
export class ProductComponent implements OnInit {
  products:Array<Product>=[]
  shoppingCart:ShoppingCart;
  constructor(private productservice:ProductServiceService,private cartservice:CartService,private authservice:AuthService) { 
    
  }

 async ngOnInit():Promise<void>{
    
    this.shoppingCart=await this.cartservice.getCart();
    this.productservice.getAll().subscribe(
      response=>this.products=response
    )
        
      
    
    
}


}
