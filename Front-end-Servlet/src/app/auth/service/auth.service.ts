import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { baseAPI } from 'src/app/baseAPI/baseAPI';
import { User } from 'src/app/interface/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userSubject: BehaviorSubject<User | null>;
  public user: Observable<User | null>;
  
  constructor(private router: Router,
    private http: HttpClient) {

      this.userSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('user')!));
        this.user = this.userSubject.asObservable();
   }
   public get userValue() {
    console.log(this.userSubject.value)
      return this.userSubject.value;
    }
    
   login(email: string, password: string) {
    return this.http.post<any>(`${baseAPI.apiBaseUrl}/auth?type=login`, { email, password })
        .pipe(map(user => {
          console.log(user)
            // store user details and jwt token in local storage to keep user logged in between page refreshes
            localStorage.setItem('user', JSON.stringify(user));
            this.userSubject.next(user);
            return user;
        }));
  
}
logout() {
  // remove user from local storage to log user out
  localStorage.removeItem('user');
  this.userSubject.next(null);
  this.router.navigate(['/login']);
}
}
