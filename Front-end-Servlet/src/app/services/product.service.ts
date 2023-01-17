import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { baseAPI } from '../baseAPI/baseAPI';
import { Category } from '../interface/category';
import { Product } from '../interface/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiServerUrl = baseAPI.apiBaseUrl;
  constructor(private http:HttpClient) { 
    
   }
  product = new BehaviorSubject<Product[]>([]);
  category = new BehaviorSubject<Category[]>([]);

  public deleteProduct(id):Observable<Product[]>{
    return this.http.delete<Product[]>(this.apiServerUrl+'/product/'+id);
  }

  public addProduct(formData:FormData):Observable<FormData>{
    console.log(formData,"HI")
    return this.http.post<FormData>(this.apiServerUrl+"/product",formData)
  }
  public updateProduct(formData:FormData,id):Observable<FormData>{
    console.log(formData,"HI")
    return this.http.put<FormData>(this.apiServerUrl+"/product/"+id,formData)
  }

}
