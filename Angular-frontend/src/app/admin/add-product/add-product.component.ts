import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder,Validators } from '@angular/forms';
import { ProductServiceService } from 'src/app/services/product-service.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  productForm:any;
  data:any;
  submit:boolean=false;
  constructor(private formBuilder: FormBuilder,private productservice:ProductServiceService) { }

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
    alert("All feilds are mandatory");
      return;
  }else{
this.data=this.productForm.value;
this.productservice.addProduct(this.data)
.then(json => {console.log(json)
alert("Product Added");
this.productForm.disable();
}); 
  }
}

}
