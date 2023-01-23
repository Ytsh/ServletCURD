import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Cart } from 'src/app/interface/cart';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent {
  constructor(private cookieService:CookieService)
  {}
  carts:Cart[] = [];
  cart:Cart = {} as Cart;
  totalCost:number = 0;

  ngOnInit(): void {
    let v = this.getCookie()
    if(v){
      this.carts = JSON.parse(v);
      console.log("carts",this.carts);
      this.updateTotalCost();
    }
  }

  updateTotalCost():void{
    this.totalCost = 0;
    this.carts.forEach(element => {
      this.totalCost +=element.quantity*parseFloat(element.product.price)
    });
  }

  getCookie():any{
    let cartss = this.cookieService.get('cart');
    return cartss
  }


  addtocart(quantity,product):void{
    // this.totalCost = 0;
    
    this.carts = [];
    // console.log("quantity",quantity);
    this.cart.quantity = parseInt(quantity) ;
    this.cart.product = product;
    // console.log("thiscart",this.cart)
    // console.log("getcookiebefore",this.getCookie());
    let cookieReceived = this.getCookie();
    if (cookieReceived){
      this.carts = JSON.parse(cookieReceived);
      console.log(this.carts)
      const found = this.carts.some(el=> el.product.id===this.cart.product.id)
      if (found){
        console.log("FOUND")
        this.carts.forEach(element => {
          if(element.product.id===this.cart.product.id){
            element.quantity=this.cart.quantity;
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
    this.updateTotalCost();

    // console.log("getcookieafter",JSON.parse(this.getCookie()));

  }

  deleteCart(product):void{

    let cookieReceived = this.getCookie();
    this.carts = JSON.parse(cookieReceived);

    this.carts = this.carts.filter((item)=>item.product.id !==product.id)
    this.updateTotalCost();
    this.setCookie(this.carts);

  }

  setCookie(cart:Cart[]):void{
    this.cookieService.set('cart',JSON.stringify(cart));
  }
}
