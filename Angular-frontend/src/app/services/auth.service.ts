import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { AppError } from '../error/app-error';
import { UnAuthError } from '../error/un-auth-error';
import { BadInputError } from '../error/bad-input-error';
import { NotFoundError } from '../error/not-found-error';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  apiURL:string="http://localhost:8080/api/";
  constructor(private http:HttpClient) { }

  register(formData:any){
    return this.http.post(this.apiURL+"auth/signup",formData).pipe
    (
      map(response=>JSON.parse(JSON.stringify(response))),
      catchError(this.handleError)
    )
  }
  login(formData:any){
    return this.http.post(this.apiURL+"auth/signin",formData).pipe
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
