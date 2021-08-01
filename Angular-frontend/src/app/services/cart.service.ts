import { Injectable } from '@angular/core';
import { AppError } from '../error/app-error';
import { Observable, Subject, throwError } from 'rxjs';
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
  public cart: Subject<ShoppingCart> = new Subject();
  constructor(private http:HttpClient,private authservice:AuthService) { 
this.initializeCart();

  }

  
   async initializeCart(){
   let temp=await this.getCart();
   //console.log("initalized");
   this.cart.next(temp); 

  }

  public getShoppingCart(){
    return this.cart;
  }

  public async updateShoppingCart(cart:ShoppingCart){
    let temp=await this.updateCart(cart);
    this.cart.next(temp);
  }
  public async deleteProductFromCart(product:Product,cartId:any){
    let temp=await this.deleteProduct(product,cartId);
    this.cart.next(temp);
  }

private deleteProduct(product:Product,cartId:any):Promise<any>{
  return this.http.get(this.apiURL+"del-product-order/"+cartId+"/"+product.id,{
    headers:myheader()
  }).toPromise().then
  (
    response=>{
      let data=JSON.parse(JSON.stringify(response));
      
      return new ShoppingCart(data.id,this.authservice.getUser(),data.order_product) ;
    })
    .catch(error=>{
      this.handleError(error);
    }
    )
  }

private getCart():Promise<any>{
return this.http.get(this.apiURL+"orders/"+this.authservice.getTokenObject().userId,{
  headers:myheader()
}).toPromise().then
(
  response=>{
    let data=JSON.parse(JSON.stringify(response));
    
    return new ShoppingCart(data.id,this.authservice.getUser(),data.order_product) ;
  })
  .catch(error=>{
    this.handleError(error);
  }
  )
}

private updateCart(cart:ShoppingCart):Promise<any>{
  
  return this.http.post(this.apiURL+"updateOrders",cart,{
    headers:myheader()
  }).toPromise()
  .then
  (
    response=>{
      let data=JSON.parse(JSON.stringify(response));
      
      return new ShoppingCart(data.id,this.authservice.getUser(),data.order_product);
    }
  )
  .catch(error=>{
    this.handleError(error);
  })
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





}