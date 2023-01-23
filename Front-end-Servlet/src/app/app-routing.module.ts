import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/gaurds/auth.guard';
import { CategoryProductComponent } from './customer/category-product/category-product.component';
import { LoginComponent } from './signing/login/login.component';

const routes: Routes = [
  {path: '', component: CategoryProductComponent},
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'cart', 
    loadChildren: () => import('./customer/cart/cart.module').then(m => m.CartModule),
    canActivate: [AuthGuard],
    data: { roles: ["admin"]}
  },
  {
    path: 'category', 
    loadChildren: () => import('./category/category.module').then(m => m.CategoryModule),
    canActivate: [AuthGuard],
    data: { roles: ["admin"]}
  },
  {
    path: 'product/:id', 
    loadChildren: () => import('./product/product.module').then(m => m.ProductModule),
    canActivate: [AuthGuard],
    data: { roles: ["admin"]}
  },

  {
    path: '',
    redirectTo: '',
    pathMatch: 'full'
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
