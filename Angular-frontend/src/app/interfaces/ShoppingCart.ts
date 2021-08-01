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
this.order_product=order_product.sort((a,b)=>a.id  <  b.id?-1:1);
    }

    addProduct(product:Product){
        this.order_product.push({
            id:0,
            product:product,
            quantity:1
        })
    }
    incrementCartProductQuantity(product:Product){
        var index=this.getProductIndex(product);
        this.order_product[index].quantity=this.order_product[index].quantity.valueOf()+1;
        
      }
      
    decrementCartProductQuantity(product:Product){
        var index=this.getProductIndex(product);
        this.order_product[index].quantity=this.order_product[index].quantity.valueOf()-1;
      
      } 
     
      
       removeProductFromCart(product:Product){
        var index=this.getProductIndex(product);
        if(index>-1){
            this.order_product.splice(index,1);
        }
      }

      getTotalCost(){
          var cost=this.order_product.reduce((total, item)=> { return total + item.quantity.valueOf()*item.product.price.valueOf() }, 0);
          return cost;
      }
      getTotalProducts(){
          var total=this.order_product.reduce((count, item)=> { return count + item.quantity.valueOf() }, 0);
          return total;
      }
      getTotalUniqueProducts(){
          return this.order_product.length;
      }
      private getProductIndex(product:Product){
        return this.order_product.findIndex(item=>item.product.id===product.id);
    }
   

}


