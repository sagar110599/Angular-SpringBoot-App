import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { Observable } from 'rxjs';
import { ShoppingCart } from '../interfaces/ShoppingCart';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  totalItems:any=0;
  constructor(private cartservice:CartService) { }

  ngOnInit(): void {
    this.cartservice.getShoppingCart().subscribe(value=>{
      this.totalItems=value.getTotalProducts();
    })
    

  }

}
