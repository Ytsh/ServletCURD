import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { ProductDialogboxComponent } from '../dialogbox/product-dialogbox/product-dialogbox.component';
import { Product } from '../interface/product';
import { CategoryService } from '../services/category.service';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent {
  constructor(private route: ActivatedRoute, private readonly http: HttpClient, private readonly categoryService:CategoryService,
    private readonly productService:ProductService,private productDialog: MatDialog) {
    
  }
  product:Product[] = [];

  public id: string;
  dataSource: MatTableDataSource<Product>;


  getProducts():void{
    this.id = this.route.snapshot.paramMap.get('id');
    // console.log(this.id);
    this.categoryService.getProductsbyCatId(this.id);
    this.categoryService.product.subscribe({
      next: response => {
        // this.category = response.map()
        this.product = response;

        this.dataSource = new MatTableDataSource(response);
        console.log(response);
    }});

  }

  ngOnInit() {
      this.getProducts()
      }
      displayedColumns: string[] = ['id', 'name','description', 'price', 'image', 'action'];
  
  addProduct() {
    this.productDialog.open(ProductDialogboxComponent, {
      width: '70%'
    }).afterClosed().subscribe(val => {
      if(val == 'save') {
        this.getProducts();
      }
    });
  }

  editProduct(row): void {
    this.productDialog.open(ProductDialogboxComponent, {
      width: '70%',
      data: row
    }).afterClosed().subscribe(val => {
      if(val == 'update') {
        this.getProducts();
      }
    });
  }

  deleteProduct(id: number): void {
    this.productService.deleteProduct(id)
      .subscribe({
        next: () => {
          this.getProducts();
        },
        error: (err: HttpErrorResponse) => {
          alert(err.message);
        }
      })
  }


}
