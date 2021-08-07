import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductComponent } from './product/product.component';

import { AdminComponent } from './admin/admin.component';
import { AdminProductComponent } from './admin/admin-product/admin-product.component';
import { AddProductComponent } from './admin/add-product/add-product.component';
import { ReactiveFormsModule } from '@angular/forms';
import { EditProductComponent } from './admin/edit-product/edit-product.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { ProductCardComponent } from './product-card/product-card.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MyCartComponent } from './my-cart/my-cart.component';
import { CheckOutComponent } from './check-out/check-out.component';
import { PreloaderComponent } from './preloader/preloader.component';
import { PageNotFoundComponent } from './error/page-not-found/page-not-found.component';
import { UnauthAccessComponent } from './error/unauth-access/unauth-access.component';


@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    AdminComponent,
    AdminProductComponent,
    AddProductComponent,
    EditProductComponent,
    LoginComponent,
    ProductCardComponent,
    NavbarComponent,
    MyCartComponent,
    CheckOutComponent,
    PreloaderComponent,
    PageNotFoundComponent,
    UnauthAccessComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    HttpClientModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
