import { Injectable } from '@angular/core';
import { Product } from '../interfaces/product';
@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {
  apiURL:string="http://localhost:8080/api/";
  constructor() { }

  getAllProducts(){
    return fetch(this.apiURL+ "products", {
      method: "GET",})
    }

  addProduct(formData:any):Promise<Product>{
    return fetch(this.apiURL+"products", {
      method: "POST",
      body: JSON.stringify(formData),
      headers: {
        "Access-Control-Allow-Origin":"*",
        "Access-Control-Allow-Methods":"DELETE, POST, GET, OPTIONS",
          "Content-type": "application/json"
      }
  })
  .then(response => response.json())
  .then(data => {
      
    return data.details
    
  });  
}
getProduct(id:any):Promise<Product>{
return fetch(this.apiURL+"products/"+id)
    .then(response => response.json())
    .then(data => {
   return data;
  });
}
updateProduct(formData:any):Promise<Product>{
  return fetch(this.apiURL+"updateProducts", {
    method: "POST",
    body: JSON.stringify(formData),
    headers: {
      "Access-Control-Allow-Origin":"*",
      "Access-Control-Allow-Methods":"DELETE, POST, GET, OPTIONS",
        "Content-type": "application/json"
    }
})
.then(response => response.json())
.then(data => {
  return data;

}); 
}
deleteProduct(id:any):Promise<Product>{
  return fetch(this.apiURL+"del-products/"+id)
  .then(response => response.json())
  .then(data => {
  return data;
});
}
}
