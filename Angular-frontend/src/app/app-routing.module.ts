import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProductComponent } from './admin/add-product/add-product.component';
import { AdminProductComponent } from './admin/admin-product/admin-product.component';
import { AdminComponent } from './admin/admin.component';
import { EditProductComponent } from './admin/edit-product/edit-product.component';
import { ErrorComponent } from './error/error.component';
import { AdminGuard } from './guards/admin.guard';
import { LoginComponent } from './login/login.component';
import { ProductComponent } from './product/product.component';


const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path: 'products', component: ProductComponent},
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
  {path: '**',component:ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
