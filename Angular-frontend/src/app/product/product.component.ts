import { Component, OnInit } from '@angular/core';
import { Product } from '../interfaces/product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  products:Array<Product>=[]
  constructor() { }

  ngOnInit(): void {
    fetch("http://localhost:8080/api/products")
    .then(response => response.json())
    .then(data => {
    this.products=data;
    console.log(this.products);
  });
  }

}
