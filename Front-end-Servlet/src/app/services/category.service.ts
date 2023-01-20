import { Injectable } from '@angular/core';
import { baseAPI } from '../baseAPI/baseAPI';
import { HttpClient } from '@angular/common/http';
import { Category } from '../interface/category';
import { BehaviorSubject, first, Observable } from 'rxjs';
import { Product } from '../interface/product';
import { CategoryProduct } from '../interface/category-product';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiServerUrl = baseAPI.apiBaseUrl;
  constructor(private http:HttpClient) { 
    
   }
  product = new BehaviorSubject<Product[]>([]);
  category = new BehaviorSubject<Category[]>([]);
  categoryProduct = new BehaviorSubject<CategoryProduct[]>([]);

  public getCategories():void {
    this.http.get<Category[]>(this.apiServerUrl+"/category").pipe(first()).subscribe(category => this.category.next(category));
  }
  public deleteCategory(id):Observable<Category[]>{
    return this.http.delete<Category[]>(this.apiServerUrl+'/category/'+id);
  }
  public updateCategory(formData:FormData,id):Observable<FormData>{
    console.log(formData,"HI")
    return this.http.put<FormData>(this.apiServerUrl+"/category/"+id,formData)
  }

  public addCategory(formData:FormData):Observable<FormData>{
    console.log(formData,"HI")
    return this.http.post<FormData>(this.apiServerUrl+"/category",formData)
  }
  public getProductsbyCatId(id): void{
    this.http.get<Product[]>(this.apiServerUrl+"/product/"+id).pipe(first()).subscribe(product => this.product.next(product));
  }
  public getCategoriesWithProducts():void {
    this.http.get<CategoryProduct[]>(this.apiServerUrl+"/category/all").pipe(first()).subscribe(categoryProduct => this.categoryProduct.next(categoryProduct));
  }

}
