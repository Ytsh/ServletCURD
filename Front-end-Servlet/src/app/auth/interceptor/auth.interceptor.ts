import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { baseAPI } from 'src/app/baseAPI/baseAPI';
import { AuthService } from '../service/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthService) { }


  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const user = this.authenticationService.userValue;
    // console.log("hi")
        const isLoggedIn = user?.token;
        const isApiUrl = request.url.startsWith(baseAPI.apiBaseUrl);
        // console.log("token",user)
        if (isLoggedIn && isApiUrl) {
          console.log(request)
          console.log(user.token)
            request = request.clone({
                setHeaders: {
                    Authorization: `${user.token}`
                }                
            });
        }
        console.log(request)

    return next.handle(request);
  }
}
