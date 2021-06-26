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
  console.log(this.products[0].product_name);
});
  }

}
