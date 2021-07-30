import { Injectable } from '@angular/core';
import { AppError } from '../error/app-error';
import { Observable, throwError } from 'rxjs';
import { UnAuthError } from '../error/un-auth-error';
import { BadInputError } from '../error/bad-input-error';
import { NotFoundError } from '../error/not-found-error';
import { catchError, map } from 'rxjs/operators';
import { myheader } from './header';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { ShoppingCart } from '../interfaces/ShoppingCart';
import {Product } from '../interfaces/product';
import { analyzeAndValidateNgModules } from '@angular/compiler';
@Injectable({
  providedIn: 'root'
})
export class CartService {
  apiURL:string="http://localhost:8080/api/";
  
  constructor(private http:HttpClient,private authservice:AuthService) { }

  


getCart():Promise<ShoppingCart>{
return this.http.get(this.apiURL+"orders/"+this.authservice.getTokenObject().userId,{
  headers:myheader()
}).toPromise().then
(
  response=>{
    let data=JSON.parse(JSON.stringify(response));
    data.user=this.authservice.getUser();
    return data;
  }
  
)
}
deleteCart(id:any){
  return this.http.get(this.apiURL+"del-orders/"+id,{
    headers:myheader()
  }).pipe
  (
    map(response=>JSON.parse(JSON.stringify(response))),
    catchError(this.handleError)
  )
}


async addToCart(product:Product){
  var cart:ShoppingCart;
  cart =await this.getCart();
      if(this.check(cart,product))
        return this.incrementCartProductQuantity(cart,product);
      else
        return this.addNewProductToCart(cart,product);
}







handleError(error:Response){
  if(error.status==400)
      return throwError(new BadInputError(error));
  if(error.status==404)
      return throwError(new NotFoundError());
  if(error.status==401)
      return throwError(new UnAuthError(error));
  return throwError(new AppError(error));
}




private updateCart(cart:ShoppingCart):Observable<ShoppingCart>{
  
  return this.http.post(this.apiURL+"updateOrders",cart,{
    headers:myheader()
  }).pipe
  (
    map(response=>{
      let data=JSON.parse(JSON.stringify(response));
      data.user=this.authservice.getUser();
      return data;
    }),
    catchError(this.handleError)
  )
    
}


private check(cart:ShoppingCart,product:Product){
  return cart.order_product.some(function(el) {
    return el.product.id === product.id;
  });
}
private  incrementCartProductQuantity(cart:ShoppingCart,product:Product){
  var index=cart.order_product.findIndex(item=>item.product.id===product.id);
  cart.order_product[index].quantity=cart.order_product[index].quantity.valueOf()+1;
  return this.updateCart(cart);
}

private decrementCartProductQuantity(cart:ShoppingCart,product:Product){
  var index=cart.order_product.findIndex(item=>item.product.id===product.id);
  cart.order_product[index].quantity=cart.order_product[index].quantity.valueOf()-1;
  return this.updateCart(cart);

} 
private addNewProductToCart(cart:any,product:Product){
  
  cart.order_product.push({
      id:0,
      product:product,
      quantity:1
  })
  return this.updateCart(cart);

}  
private deleteFromCart(cart:ShoppingCart,product:Product){
  cart.order_product.pop();
  return this.updateCart(cart);

}


}