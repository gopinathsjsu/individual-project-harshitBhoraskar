package com.cart_management;

import com.cart_management.Category;
import com.cart_management.Cart;
import com.cart_management.EssentialTotal;
import com.cart_management.Items;

import java.util.HashMap;
import java.util.Set;

public class Essentials implements Cart {

    private  Cart nextInCart;

    // Defines the next Object to receive the
    // data if this one can't use it

    public void setCartCategory(Cart nextCart) {

        setCartCategory = nextCart;

    }

    public double calculateTotal(HashMap<String, Items> cart , String item , int quantity, Set<String> wrongQuantities,
                                 Set<String> readEssentials,Set<String> readLuxury,Set<String> readMisc)
    {
        Items items = cart.get(item);

        if(items.Category == "Essential"){
            readEssentials.add(item);
            if(quantity<=items.getQuantity()){
                EssentialTotal.setEssentialCount(quantity);
                items.setQuantity(items.getQuantity()-quantity);
                return quantity* items.getPrice();
            }
			else{
                error.add(item);
                return 0;
            }
        }
        else {
            return setCartCategory.calculateTotal(cart,item , quantity,error,readEssentials,readLuxury,readMisc);
        }
    }
}