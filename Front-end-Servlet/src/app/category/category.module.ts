import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryComponent } from './category.component';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { CategoryDialogboxComponent } from '../dialogbox/category-dialogbox/category-dialogbox.component';
import { ProductComponent } from '../product/product.component';

const routes: Routes = [
  {path: '', component: CategoryComponent}
];

@NgModule({
  declarations: [
    CategoryComponent,
    CategoryDialogboxComponent
],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule.forChild(routes)
  ]
})
export class CategoryModule { }
