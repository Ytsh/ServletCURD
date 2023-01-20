import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StoreModule } from '@ngrx/store';
import { CategoryComponent } from './category/category.component';
import { ProductComponent } from './product/product.component';
import { CategoryDialogboxComponent } from './dialogbox/category-dialogbox/category-dialogbox.component';
import { ProductDialogboxComponent } from './dialogbox/product-dialogbox/product-dialogbox.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HeaderComponent } from './component/header/header.component';
import { MaterialModule } from './material/material.module';
import { LoginComponent } from './signing/login/login.component';
import { AuthService } from './auth/service/auth.service';
import { AuthInterceptor } from './auth/interceptor/auth.interceptor';
import { CategoryProductComponent } from './customer/category-product/category-product.component';
import { CookieService } from 'ngx-cookie-service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    CategoryProductComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MaterialModule
    // StoreModule.forRoot({}, {})
  ],
  providers: [AuthService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
