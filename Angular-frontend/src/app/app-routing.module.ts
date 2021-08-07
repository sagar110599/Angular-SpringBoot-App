import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProductComponent } from './admin/add-product/add-product.component';
import { AdminProductComponent } from './admin/admin-product/admin-product.component';
import { AdminComponent } from './admin/admin.component';
import { EditProductComponent } from './admin/edit-product/edit-product.component';
import { CheckOutComponent } from './check-out/check-out.component';
import { PageNotFoundComponent } from './error/page-not-found/page-not-found.component';
import { UnauthAccessComponent } from './error/unauth-access/unauth-access.component';
import { AdminGuard } from './guards/admin.guard';
import { LoginComponent } from './login/login.component';
import { MyCartComponent } from './my-cart/my-cart.component';
import { ProductComponent } from './product/product.component';


const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path: 'products', component: ProductComponent},
  {path: 'checkout', component: CheckOutComponent},
  {path: 'cart', component: MyCartComponent},
  {path: 'admin',children:[
  {path: 'products', canActivate:[AdminGuard],
  component: AdminProductComponent},
  {path: 'products/:id', canActivate:[AdminGuard],
  component: EditProductComponent},
  {path: 'add-product', canActivate:[AdminGuard],
  component: AddProductComponent},
  {path:'',
  component:AdminComponent 
  }
]},
  {path:"forbidden",component:UnauthAccessComponent},
  {path: '**',component:PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
