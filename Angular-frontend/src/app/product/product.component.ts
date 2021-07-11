import { Component, OnInit } from '@angular/core';
import { Product } from '../interfaces/product';
import { ProductServiceService } from '../services/product-service.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  products:Array<Product>=[]
  constructor(private productservice:ProductServiceService) { }

  ngOnInit(): void {
    this.productservice.getAll().subscribe(
      response=>this.products=response
    )
    
  }

}
