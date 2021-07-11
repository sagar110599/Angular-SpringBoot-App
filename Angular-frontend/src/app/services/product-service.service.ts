import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../interfaces/product';
import { catchError, map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {
  apiURL:string="http://localhost:8080/api/";
  
  constructor(private http:HttpClient) { }

  getAll(){
    return this.http.get(this.apiURL+"products").pipe
    (
      map(response=>JSON.parse(JSON.stringify(response)))
    )
  }

  post(formData:any){
    return this.http.post(this.apiURL+"products",formData).pipe
    (
      map(response=>JSON.parse(JSON.stringify(response)))
    ) 
}

get(id:any){
return this.http.get(this.apiURL+"products/"+id).pipe
(
  map(response=>JSON.parse(JSON.stringify(response)))
)
}


update(formData:any){
  return this.http.post(this.apiURL+"updateProducts",formData).pipe
  (
    map(response=>JSON.parse(JSON.stringify(response)))
  )
    
}


delete(id:any){
  return this.http.get(this.apiURL+"del-products/"+id).pipe
  (
    map(response=>JSON.parse(JSON.stringify(response)))
  )
}
}
