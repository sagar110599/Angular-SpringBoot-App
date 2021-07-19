import { Component, OnInit } from '@angular/core';
 
import { FormGroup, FormControl,Validators } from '@angular/forms';
import { environment } from '../../environments/environment';
import { UnAuthError } from '../error/un-auth-error';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  public classname = "container";
  submitLogin:boolean=false;
  submitRegister:boolean=false;
  

  constructor(private authservice:AuthService) {

  }
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });
  registerForm = new FormGroup({
    username: new FormControl('',[Validators.required]),
    email: new FormControl('',[Validators.required]),
    password: new FormControl('',[Validators.required]),
  });

  slideToSignup() {
    this.classname = "container right-panel-active"
  }

  slideToSignin() {
    this.classname = "container";
  }
  
register(){
  if(!this.submitRegister){
    this.submitRegister=!this.submitRegister;
    } 
    if(this.registerForm.valid){ 
this.authservice.register(this.registerForm.value).subscribe(

  response=>{
    console.log(response);
  },
    error=>{
      console.log(error);
      alert(error.originalError.error.message);
    }
    
  )
    }
}
get l() { return this.loginForm.controls; } 
get r() { return this.registerForm.controls; } 

login(){
  if(!this.submitLogin){
  this.submitLogin=!this.submitLogin;
  }
  this.authservice.login(this.loginForm.value).subscribe(
    response=>{
      console.log(response);
      alert("Successful Login");
    },
    error=>{
      if(error instanceof UnAuthError){
        console.log(error);
          this.loginForm.setErrors(
            {'incorrect': true}  
          )
      }

    })
}

  ngOnInit(): void {
  }
}
