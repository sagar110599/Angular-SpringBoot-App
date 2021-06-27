import { Component, OnInit } from '@angular/core';
import {Product} from '../../interfaces/product';
@Component({
  selector: 'app-admin-product',
  templateUrl: './admin-product.component.html',
  styleUrls: ['./admin-product.component.css']
})
export class AdminProductComponent implements OnInit {
  apiurl:string="http://localhost:8080/api/products";
  
  products:Array<Product>=[]
  constructor() { }

  ngOnInit(): void {
    fetch(this.apiurl)
  .then(response => response.json())
  .then(data => {
  this.products=data;
  console.log(this.products[0]);
});
  
  
}
getSearch(event:any){
  if(event.target.value==""){
    this.ngOnInit();
  }else{
  this.products=this.products.filter(obj=>(obj.product_name.toLocaleLowerCase()).startsWith((event.target.value).toLocaleLowerCase())||
  (obj.product_desc.toLocaleLowerCase()).startsWith((event.target.value).toLocaleLowerCase()))
  }
  
}

}
