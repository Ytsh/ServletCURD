import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { CategoryDialogboxComponent } from '../dialogbox/category-dialogbox/category-dialogbox.component';
import { Category } from '../interface/category';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent {
  constructor(private readonly http: HttpClient, private readonly categoryService:CategoryService,
    private categoryDialog:MatDialog
    ){}

  category: Category[] = [];
  dataSource: MatTableDataSource<Category>;

  displayedColumns: string[] = ['id', 'name','description', 'action'];

  ngOnInit(): void {
    this.getCategory()
  }

  getCategory():void{
    this.categoryService.getCategories()
    this.categoryService.category.subscribe({
      next: response => {
        // this.category = response.map()
        this.category = response;
        console.log("res",response);
        this.dataSource = new MatTableDataSource(response);
      }
    });
  console.log(this.category);
  }

  addCategory() {
    this.categoryDialog.open(CategoryDialogboxComponent, {
      width: '70%'
    }).afterClosed().subscribe(val => {
      if(val == 'save') {
        this.getCategory();
      }
    }); 
  }

  editCategory(row): void {
    this.categoryDialog.open(CategoryDialogboxComponent, {
      width: '70%',
      data: row
    }).afterClosed().subscribe(val => {
      if(val == 'update') {
        this.getCategory();
      }
    });
  }

  deleteCategory(id: number): void {
    this.categoryService.deleteCategory(id)
      .subscribe({
        next: () => {
          this.getCategory();
        },
        error: (err: HttpErrorResponse) => {
          alert(err.message);
        }
      })
  }

}
