import { Component, OnInit,OnDestroy } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import Swal from 'sweetalert2';
import { Product } from '../interfaces/product';
import { CartItem, ShoppingCart } from '../interfaces/ShoppingCart';
import { AuthService } from '../services/auth.service';

import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-my-cart',
  templateUrl: './my-cart.component.html',
  styleUrls: ['./my-cart.component.css']
})
export class MyCartComponent implements OnInit {
  shoppingCart:ShoppingCart;
  cartSubscription:any;
  constructor(private cartservice:CartService) {
   
   }

  async ngOnInit():Promise<void>{
    
    this.cartservice.initializeCart();
    
    this.cartSubscription=this.cartservice.getShoppingCart().subscribe(value=>{
     this.shoppingCart=value;
    })
    
}
ngOnDestroy():void{
  console.log("My-Cart page destroyed");
  this.cartSubscription.unsubscribe();
}
async incrementQuantity(item:CartItem){
  this.shoppingCart.incrementCartProductQuantity(item.product);
    await this.cartservice.updateShoppingCart(this.shoppingCart);
   console.log(this.shoppingCart);
}
async decrementQuantity(item:CartItem){
  if(item.quantity==1){
    this.ConfirmSwal().then(async (result) => {
      if (result.isConfirmed) {
    this.shoppingCart.removeProductFromCart(item.product);
    await this.cartservice.deleteProductFromCart(item.product,this.shoppingCart.id);
        Swal.fire(
          'Deleted!',
          'Product ',
          'success'
        )
      
      }
    })
    
  }else{
  this.shoppingCart.decrementCartProductQuantity(item.product);
    await this.cartservice.updateShoppingCart(this.shoppingCart);
    console.log(this.shoppingCart);
}
}
private ConfirmSwal(){
  return Swal.fire({
    title: 'Are you sure?',
    text: 'Product will be removed from Cart!!',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Yes, delete it!',
    cancelButtonText: 'No, keep it'
  })
}

}
