import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  productForm:any;
  data:any;
  constructor() { }

  ngOnInit(): void {
    this.productForm = new FormGroup({
      product_name: new FormControl(''),
      product_desc: new FormControl(''),
      price:new FormControl(''),
    });
  }
  onSubmit(){
    this.data=this.productForm.value;
    console.log(this.data);
    fetch("http://localhost:8080/api/products", {
    method: "POST",
    body: JSON.stringify(this.data),
    headers: {
      "Access-Control-Allow-Origin":"*",
      "Access-Control-Allow-Methods":"DELETE, POST, GET, OPTIONS",
        "Content-type": "application/json"
    }
})
.then(response => response.json())
.then(json => console.log(json));
  }

}
