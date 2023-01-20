import { ProductDTO } from "./product-dto";

export interface CategoryProduct {
    id:Number,
    categoryName:String,
    categoryDescription:String,
    product:ProductDTO[]
}
