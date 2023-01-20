import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/service/auth.service';
import { Cart } from 'src/app/interface/cart';
import { Category } from 'src/app/interface/category';
import { CategoryProduct } from 'src/app/interface/category-product';
import { Product } from 'src/app/interface/product';
import { User } from 'src/app/interface/user';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-category-product',
  templateUrl: './category-product.component.html',
  styleUrls: ['./category-product.component.scss']
})
export class CategoryProductComponent {

  categoryId: number;
  products: Product[] = [];
  category: Category[];
  categoryProduct : CategoryProduct[];
  carts:Cart[];
  cart:Cart = {} as Cart;
  user:User
  btnbool:boolean;
  private subscription: Subscription;
  cartCount: number;
  
  constructor(private readonly route: ActivatedRoute,
    private readonly categoryService: CategoryService,
    private readonly productService: ProductService,
    private readonly router: Router,
    // private cartService:CartService,
    private authService:AuthService ) {}

  ngOnInit(): void {
    this.btnbool = false
    const id = this.route.snapshot.paramMap.get('id');
    if(id) {
      this.categoryId = +id;
    }
    // this.subscription = this.cartService.cart.subscribe(val => this.carts = val);
    this.subscription = this.authService.user.subscribe(val => this.user = val);

    this.categoryService.getCategoriesWithProducts();
    this.categoryService.categoryProduct.subscribe({
      next: (response) => {
        this.categoryProduct = response;
        console.log("responsess",this.categoryProduct);
      }
    });
    // console.log(this.category);
    // setTimeout(() => {
    //   this.getChildCategories(this.categoryId);
    // },1000);
  }
  isRoleAvailable(R:string):boolean
  {

    if(this.user.role.includes(R))
    {
      return true;
    }
    else{
      return false;
    }
  }
  
  addtocart(quantity,product):void{


    this.cart.quantity = quantity
    this.cart.productId = product
    console.log("thiscart",this.cart)


    }
    

  getProductByCategory(childCategoryId: number): void {       
    this.router.navigate(['/products', childCategoryId]).then();
  }
}
