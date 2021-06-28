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
  console.log(this.products);
});
  
  
}
getSearch(event:any){
  if(event.target.value==""){
    this.ngOnInit();
  }else{
  this.products=this.products.filter(obj=>(obj.product_name.toLocaleLowerCase()).includes((event.target.value).toLocaleLowerCase())||
  (obj.product_desc.toLocaleLowerCase()).includes((event.target.value).toLocaleLowerCase()))
  }
  
}
delete(id:any){
  
  var confirmDelete=confirm("Do you Wish to delete the item");
  if(confirmDelete){
  fetch("http://localhost:8080/api/del-products/"+(id.target.value))
  .then(response => response.json())
  .then(data => {
  console.log(data);
  this.ngOnInit();
});
}
}

}
