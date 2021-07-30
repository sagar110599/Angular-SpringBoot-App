import { CartService } from "../services/cart.service";
import { Product} from "./product";
export interface User{
    id:Number;
    username:string;
}
export interface CartItem{
    id:Number;
    product:Product;
    quantity:Number;
}
export class ShoppingCart{
    id:Number;
    user:User;
    order_product:CartItem[];
    constructor(id:Number,user:User,order_product:Array<CartItem>){
this.id=id;
this.user=user;
this.order_product=order_product;
    }

    addProduct(product:Product){
     var index=this.order_product.findIndex(item=>item.product.id===product.id);
     if(index>-1){
        this.incrementCartProductQuantity(product,index)
     }else{
         this.addNewProductToCart(product);
     }
    }
    private  incrementCartProductQuantity(product:Product,index:any){
        
        this.order_product[index].quantity=this.order_product[index].quantity.valueOf()+1;
        
      }
      
      private decrementCartProductQuantity(product:Product,index:any){
        
        this.order_product[index].quantity=this.order_product[index].quantity.valueOf()-1;
      
      } 
      private addNewProductToCart(product:Product){
        
        this.order_product.push({
            id:0,
            product:product,
            quantity:1
        })
        
      
      } 

}


