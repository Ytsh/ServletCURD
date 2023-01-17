import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from 'src/app/interface/category';
import { Product } from 'src/app/interface/product';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-dialogbox',
  templateUrl: './product-dialogbox.component.html',
  styleUrls: ['./product-dialogbox.component.scss']
})
export class ProductDialogboxComponent implements OnInit {

  productForm: FormGroup;
  categories:Category[];
  actionBtn: string = "Save";
  actionLabel: string = "Add";
  constructor(private readonly formBuilder: FormBuilder,
    public dialog:MatDialog,
    private readonly categoryService: CategoryService,
    private readonly productService: ProductService,
    private productDialogRef: MatDialogRef<Product>,
    @Inject(MAT_DIALOG_DATA) public data: Product){
    // console.log("DB")
    // console.log(data)
  }
  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      'id': ['0'],
      'productName': ['', Validators.required],
      'price': ['', Validators.required],
      // 'quantity': ['', Validators.required],
      'image': [''],
      'productDescription': ['', Validators.required],
      'categoryId': ['', Validators.required],
    });
  this.productForm = this.formBuilder.group({
    'id': ['0'],
    'productName': ['', Validators.required],
    'price': ['', Validators.required],
    // 'quantity': ['', Validators.required],
    'image': [''],
    'productDescription': ['', Validators.required],
    'categoryId': ['', Validators.required],
  });
  this.categoryService.getCategories()
  this.categoryService.category .subscribe({
    next:(response) =>{
      this.categories = response;
    },
    error: (err:HttpErrorResponse)=>{
      alert(err.message)
    }
  })
  if(this.data) {
    this.actionBtn = "Update";
    this.actionLabel = "Update";
    this.productForm.controls['id'].setValue(this.data.id);
    this.productForm.controls['productName'].setValue(this.data.productName);
    this.productForm.controls['price'].setValue(this.data.price);
    // this.productForm.controls['quantity'].setValue(this.data.quantity);
    this.productForm.controls['productDescription'].setValue(this.data.productDescription);
    this.productForm.controls['image'].setValue(this.data.image);
    this.productForm.controls['categoryId'].setValue(3);
  }
  }

  addProduct(): void {
    const product = this.productForm.value;
    const formData = new FormData();
    // let id = product.category;
    // product.category = this.categories.find(items=>items.id == id);
    console.log(JSON.stringify(product))
    
    // console.log(JSON.stringify(product))
    // console.log(product)
    // formData.append('product', JSON.stringify(product));
    // console.log(formData)
    // formData.append('file', this.productFile);
    if(!this.data){
      this.productService
        .addProduct(product)
        .subscribe({
          next: () => {
            this.productForm.reset();
            this.productDialogRef.close("save");
          },
        error: (err: HttpErrorResponse) => {
            alert(err.message);
          }
      })
    }
    else {
      this.updateProduct(product); 
    }
  }

  updateProduct(formData: FormData): void {
    this.productService
    .updateProduct(formData,this.data.id)
    .subscribe({
      next: () => {
        this.productForm.reset();
        this.productDialogRef.close("update");
      },
      error: (err: HttpErrorResponse) => {
        alert(err.message);
      }
    })
  }


}
