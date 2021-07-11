import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from 'src/app/services/product-service.service';
import {Product} from '../../interfaces/product';
@Component({
  selector: 'app-admin-product',
  templateUrl: './admin-product.component.html',
  styleUrls: ['./admin-product.component.css']
})
export class AdminProductComponent implements OnInit {
  apiurl:string="http://localhost:8080/api/products";
  
  products:Array<Product>=[]
  p: number = 1;
  count: number = 4;
  constructor(private productservice:ProductServiceService) { }

  ngOnInit(): void {
  this.productservice.getAll().subscribe(response=>{
    this.products=response;
  })
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
  this.productservice.delete(id.target.value).subscribe(
    response=>{
      console.log(response);
      this.ngOnInit();
    }
  )
  }
}
changePage(event:any){
  console.log(event);
  this.p=event;
}

}
