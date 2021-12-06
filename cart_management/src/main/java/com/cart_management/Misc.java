package com.cart_management;

import java.util.HashMap;
import java.util.Set;

public class Misc implements Chain{

    private  Chain nextInChain;

    @Override
    public void setNextChain(Chain nextChain) {
        nextInChain = nextChain;
    }

    @Override
    public double calculateTotal(HashMap<String, Items> cart , String item , int quantity, Set<String> wrongQuantities,
                                 Set<String> readEssentials,Set<String> readLuxury,Set<String> readMisc)    {
        Items items = inventry.get(item);
        if(items.Category == Category.MISCELLENEOUS){
            MissSeen.add(item);
            if(quantity<=items.getQuantity()){
                EssentialTotal.getMiscSum(quantity);
                items.setQuantity(quantity);
                return quantity* items.getPrice();
            }
            else{
                error.add(item);
                return 0;
            }
        }
        else {
            return nextInChain.calculateTotal(inventory,item , quantity,error,readEssentials,readLuxury,readMisc);
        }
    }
}
