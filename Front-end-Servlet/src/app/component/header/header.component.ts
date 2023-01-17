import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/interface/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  constructor(private readonly http: HttpClient, private readonly categoryService:CategoryService,
    private readonly router: Router,) { 
      
     }

  category:Category[];
  username:string = "";

  ngOnInit(): void {
    // this.getuserdetail()
  


    this.categoryService.getCategories()
    this.categoryService.category.subscribe({
      next: response => {
        // this.category = response.map()
        this.category = response;
        console.log(response);
      }
    });

    
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


}
