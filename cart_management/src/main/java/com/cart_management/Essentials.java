package com.cart_management;

//import cart_management.src.main.java.com.cart_management.Category;
import com.cart_management.Cart;
import com.cart_management.EssentialTotal;
import com.cart_management.ItemInstance;

import java.util.HashMap;
import java.util.Set;

public class Essentials implements Cart {

    private  Cart setCartCategory;

    // Defines the next Object to receive the
    // data if this one can't use it

    public void setCartCategory(Cart nextCart) {

        setCartCategory = nextCart;

    }

    public double calculateTotal(HashMap<String, ItemInstance> cart , String item , int quantity, Set<String> wrongQuantities,
                                 Set<String> readEssentials,Set<String> readLuxury,Set<String> readMisc)
    {
        ItemInstance itemInstance = cart.get(item);

        if(itemInstance.Category.equals("Essential")){
            readEssentials.add(item);
            if(quantity<=itemInstance.getQuantity()){
                EssentialTotal.setEssentialCount(EssentialTotal.getEssentialCount()- quantity);
                itemInstance.setQuantity(itemInstance.getQuantity()-quantity);
                return quantity* itemInstance.getPrice();
            }
			else{
                wrongQuantities.add(item);
                return 0;
            }
        }
        else {
            return setCartCategory.calculateTotal(cart,item , quantity,wrongQuantities,readEssentials,readLuxury,readMisc);
        }
    }
}