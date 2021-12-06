package com.cart_management;

import java.util.HashMap;
import java.util.Set;

public class Luxury implements Chain{

    private  Chain nextInChainResponsibility;

    @Override
    public void nextInChainResponsibility(Chain nextChain) {
        nextInChain = nextChain;
    }

    @Override
    public double calculateTotal(HashMap<String, Items> cart , String item , int quantity, Set<String> wrongQuantities,
                                 Set<String> readEssentials,Set<String> readLuxury,Set<String> readMisc)
    {
        Items items = inventory.get(item);
        if(items.Category == Category.LUXURY){
            luxurySeen.add(item);
            if( quantity<=items.getQuantity()){
                EssentialTotal.setLuxurySum(quantity);
                items.setQuantity(quantity);
                return quantity* items.getPrice();
            }
            else{
                error.add(item);
                return 0;
            }
        }
        else {
            return nextInChainResponsibility.calculateTotal(inventory,item , quantity,error,readEssentials,readLuxury,readMisc);
        }
    }
}
