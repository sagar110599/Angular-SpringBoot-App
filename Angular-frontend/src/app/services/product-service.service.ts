import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../interfaces/product';
import { AppError } from '../error/app-error';
import { throwError } from 'rxjs';
import { UnAuthError } from '../error/un-auth-error';
import { BadInputError } from '../error/bad-input-error';
import { NotFoundError } from '../error/not-found-error';
import { catchError, map } from 'rxjs/operators';
import { myheader } from './header';
@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {
  apiURL:string="http://localhost:8080/api/";
  
  constructor(private http:HttpClient) { }

  getAll(){
    return this.http.get(this.apiURL+"products",{
      headers:myheader()
    }).pipe
    (
      map(response=>JSON.parse(JSON.stringify(response))),
      catchError(this.handleError)
    )
  }

  post(formData:any){
    return this.http.post(this.apiURL+"products",formData,{
      headers:myheader()
    }).pipe
    (
      map(response=>JSON.parse(JSON.stringify(response))),
      catchError(this.handleError)
    ) 
}

get(id:any){
return this.http.get(this.apiURL+"products/"+id,{
  headers:myheader()
}).pipe
(
  map(response=>JSON.parse(JSON.stringify(response))),
  catchError(this.handleError)
)
}


update(formData:any){
  return this.http.post(this.apiURL+"updateProducts",formData,{
    headers:myheader()
  }).pipe
  (
    map(response=>JSON.parse(JSON.stringify(response))),
    catchError(this.handleError)
  )
    
}


delete(id:any){
  return this.http.get(this.apiURL+"del-products/"+id,{
    headers:myheader()
  }).pipe
  (
    map(response=>JSON.parse(JSON.stringify(response))),
    catchError(this.handleError)
  )
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
