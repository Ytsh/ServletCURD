import { ProductDTO } from "./product-dto";

export interface Cart {
    id: number;
    quantity: number;
    product:ProductDTO;
}
