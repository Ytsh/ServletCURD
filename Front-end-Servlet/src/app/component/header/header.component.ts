import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/service/auth.service';
import { Category } from 'src/app/interface/category';
import { User } from 'src/app/interface/user';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  constructor(private authService: AuthService,private readonly http: HttpClient, private readonly categoryService:CategoryService,
    private readonly router: Router,) { 
      
     }

     isLoggedIn: User;
     private subscription: Subscription;

  category:Category[];
  username:string = "";

  ngOnInit(): void {
    // this.getuserdetail()
  // if (this.isLoggedIn){
    this.subscription = this.authService.user.subscribe(val => this.isLoggedIn = val);

    this.categoryService.getCategories()
    this.categoryService.category.subscribe({
      next: response => {
        // this.category = response.map()
        this.category = response;
        console.log(response);
      }
    });
  // }



    
  }
  categoryCRUD(){
    this.categoryService.getCategories;
    this.router.navigate(['/category']).then();
  }

  findProductByCategoryId(id)
  {
    
    this.categoryService.getProductsbyCatId(id);
    this.router.navigate(['/product', id]).then();
  }

  backhome(){
    this.router.navigate(['/'])
   }
   login(): void {
    // this.getuserdetail()
    this.router.navigate(['/login'])
   }

 
   logout(): void {
    //  this.getuserdetail()
     this.authService.logout()
    //  this.authService.isLoggedIn$$.next(false);
   }

   isRoleAvailable(R:string):boolean
   {
 
     if(this.isLoggedIn.role.includes(R))
     {
       return true;
     }
     else{
       return false;
     }
   }


}
