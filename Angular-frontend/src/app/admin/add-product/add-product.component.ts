import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder,Validators } from '@angular/forms';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  productForm:any;
  data:any;
  submit:boolean=false;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      product_name:  ['', Validators.required],
      product_desc:  ['', Validators.required],
      price: ['', Validators.required],
      quantity: ['', Validators.required],
      product_image: ['', Validators.required],
      
    });
  }

get f() { return this.productForm.controls; }  

imageSelected(eventFile:any){
var file = eventFile.target.files[0];
var reader = new FileReader();
reader.onload = (event)=> {
  this.productForm.value.product_image=event.target?.result;
  console.log(this.productForm.value.image);
}
reader.readAsDataURL(file);
}
  
  onSubmit(){
    this.submit=true;
  if (this.productForm.invalid) {
      return;
  }else{
    this.data=this.productForm.value;
    
    
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
.then(json => {console.log(json)
alert("Product Added");
this.productForm.reset();
}); 
  }
}

}
