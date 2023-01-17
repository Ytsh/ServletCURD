import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductComponent } from './product.component';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { ProductDialogboxComponent } from '../dialogbox/product-dialogbox/product-dialogbox.component';


const routes: Routes = [
  {path: '', component: ProductComponent}
];

@NgModule({
  declarations: [ProductComponent,
  ProductDialogboxComponent],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule.forChild(routes)
  ]
})
export class ProductModule { }
