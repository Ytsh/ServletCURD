import { Category } from "./category";

export interface Product {
    id:Number,
    productName:String,
    productDescription:String,
    image:String,
    price:String,
    category:Category
}
