import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
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
  carts:Cart[] = [];
  cart:Cart = {} as Cart;
  user:User
  btnbool:boolean;
  private subscription: Subscription;
  cartCount: number;
  private cookie_name='';
  private all_cookies:any='';
  
  constructor(private readonly route: ActivatedRoute,
    private readonly categoryService: CategoryService,
    private readonly productService: ProductService,
    private readonly router: Router,
    // private cartService:CartService,
    private authService:AuthService,
    private cookieService:CookieService ) {}

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
  // isRoleAvailable(R:string):boolean
  // {

  //   if(this.user.role.includes(R))
  //   {
  //     return true;
  //   }
  //   else{
  //     return false;
  //   }
  // }
  
  addtocart(quantity,product):void{
    this.carts = [];
    console.log("quantity",quantity);
    this.cart.quantity = parseInt(quantity) ;
    this.cart.product = product;
    console.log("thiscart",this.cart)
    console.log("getcookiebefore",this.getCookie());
    let cookieReceived = this.getCookie();
    if (cookieReceived){
      this.carts = JSON.parse(cookieReceived);
      console.log(this.carts)
      const found = this.carts.some(el=> el.product.id===this.cart.product.id)
      if (found){
        console.log("FOUND")
        this.carts.forEach(element => {
          if(element.product.id===this.cart.product.id){
            element.quantity+=this.cart.quantity;
          }
          
        });
      }
      else{
        this.carts.push(this.cart);
      }
    }
    else{
      // this.carts = [];
      this.carts.push(this.cart);
    }
    this.setCookie(this.carts);

    console.log("getcookieafter",JSON.parse(this.getCookie()));


  }

  setCookie(cart:Cart[]):void{
    this.cookieService.set('cart',JSON.stringify(cart));
  }
  getCookie():any{
    let cartss = this.cookieService.get('cart');
    return cartss
  }

  getProductByCategory(childCategoryId: number): void {       
    this.router.navigate(['/products', childCategoryId]).then();
  }
}
