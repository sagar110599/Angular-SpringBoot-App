import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
email:string="";
  constructor() { }

  ngOnInit(): void {
    if(sessionStorage.getItem("isLogin")==="true"){
      window.location.href='./admin/products';
    }
  }
checkLogin(uname:string ,password:string){
  this.email=uname;
  fetch("http://localhost:8080/api/login", {
    method: "POST",
    body: JSON.stringify({'email':uname,'password':password}),
    headers: {
      "Access-Control-Allow-Origin":"*",
      "Access-Control-Allow-Methods":"DELETE, POST, GET, OPTIONS",
        "Content-type": "application/json"
    }
})
.then(response => response.json())
.then(json => {
  if(json['isLogin']==="true"){
    sessionStorage.setItem("email",this.email);
    sessionStorage.setItem("isLogin","true");
    window.location.href='./admin/products';
  }else{
    alert("Invalid Credentials");
  }

}); 
}
}
