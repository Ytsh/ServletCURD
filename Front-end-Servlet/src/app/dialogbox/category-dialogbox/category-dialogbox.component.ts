import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from 'src/app/interface/category';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-category-dialogbox',
  templateUrl: './category-dialogbox.component.html',
  styleUrls: ['./category-dialogbox.component.scss']
})
export class CategoryDialogboxComponent {

  categoryForm: FormGroup;
  categories:Category[];
  actionBtn: string = "Save";
  actionLabel: string = "Add";
  constructor(private readonly formBuilder: FormBuilder,
    public dialog:MatDialog,
    private readonly categoryService: CategoryService,
    private readonly productService: ProductService,
    private categoryDialogRef: MatDialogRef<Category>,
    @Inject(MAT_DIALOG_DATA) public data: Category){
    // console.log("DB")
    // console.log(data)
  }
  ngOnInit(): void {
    this.categoryForm = this.formBuilder.group({
      'id': ['0'],
      'categoryName': ['', Validators.required],
      'categoryDescription': ['', Validators.required],
    });
  this.categoryForm = this.formBuilder.group({
    'id': ['0'],
    'categoryName': ['', Validators.required],
    'categoryDescription': ['', Validators.required],
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
    this.categoryForm.controls['id'].setValue(this.data.id);
    this.categoryForm.controls['categoryName'].setValue(this.data.categoryName);
    this.categoryForm.controls['categoryDescription'].setValue(this.data.categoryDescription);
  }
  }

  addCategory(): void {
    const category = this.categoryForm.value;
    const formData = new FormData();
    
    if(!this.data){
      this.categoryService
        .addCategory(category)
        .subscribe({
          next: () => {
            this.categoryForm.reset();
            this.categoryDialogRef.close("save");
          },
        error: (err: HttpErrorResponse) => {
            alert(err.message);
          }
      })
    }
    else {
      this.updateCategory(category); 
    }
  }

  updateCategory(formData: FormData): void {
    this.categoryService
    .updateCategory(formData,this.data.id)
    .subscribe({
      next: () => {
        this.categoryForm.reset();
        this.categoryDialogRef.close("update");
      },
      error: (err: HttpErrorResponse) => {
        alert(err.message);
      }
    })
  }

}
