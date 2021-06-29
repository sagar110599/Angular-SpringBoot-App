import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder,Validators } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';
import { Product } from 'src/app/interfaces/product';
import { ProductServiceService } from 'src/app/services/product-service.service';
@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  productForm:any;
  product?:Product;
  data:any;
  submit:boolean=false;
  constructor(private formBuilder: FormBuilder,private route:ActivatedRoute,private productservice:ProductServiceService) { }

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      id:[''],
      product_name:  [''],
      product_desc:  [''],
      price: [''],
      quantity: [''],
      product_image: [''],
      
    });
    
  this.productservice.getProduct(this.route.snapshot.params.id)
  .then(data => {
   this.product=data;
    console.log(this.product);
    this.setFormFields(this.product);
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
    alert("Invalid Form.Fields Remaining");
      return;
  }else{
    this.data=this.productForm.value;
    
    
this.productservice.updateProduct(this.data)
.then(json => {console.log(json)
alert("Product updated");
this.ngOnInit();
});  
  }
}

setFormFields(pro:any){
  this.productForm.setValue({
    id:pro.id,
    product_name:  pro.product_name,
    product_desc:  pro.product_desc, 
    price: pro.price, 
    quantity: pro.quantity, 
    product_image: pro.product_image,
    
  });
  
}

}
