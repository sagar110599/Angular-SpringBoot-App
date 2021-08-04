import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { myheader } from './header';
import { AppError } from '../error/app-error';
import { Observable, Subject, throwError } from 'rxjs';
import { UnAuthError } from '../error/un-auth-error';
import { BadInputError } from '../error/bad-input-error';
import { NotFoundError } from '../error/not-found-error';
@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  apiURL:string="http://localhost:8080/api/payment/";
  constructor(private http:HttpClient) { }

  public getRpayOrder(orderRequest:any):Promise<any>{
    return this.http.post(this.apiURL+"start",orderRequest,{
      headers:myheader()
    }).toPromise().then
    (
      response=>{
        let data=JSON.parse(JSON.stringify(response));
        return data ;
      })
      .catch(error=>{
        this.handleError(error);
      }
      )
    }
    public checkPayment(transaction:any):Promise<any>{
      return this.http.post(this.apiURL+"verify",transaction,{
        headers:myheader()
      }).toPromise().then
      (
        response=>{
          let data=JSON.parse(JSON.stringify(response));
          return data ;
        })
        .catch(error=>{
          this.handleError(error);
        }
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
