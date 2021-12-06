package com.inventory;

import com.inventory.Category;
import com.inventory.Chain;
import com.inventory.EssentialTotal;
import com.inventory.Items;

import java.util.HashMap;
import java.util.Set;

public class Essentials implements Chain {

    private  Chain nextInChain;

    // Defines the next Object to receive the
    // data if this one can't use it

    public void setNextChainResponsibility(Chain nextChain) {

        setNextChainResponsibility = nextChain;

    }

    public double calculateTotal(HashMap<String, Items> inventory , String item , int quantity, Set<String> error,
                                 Set<String> readEssentials,Set<String> readLuxury,Set<String> readMisc)
    {
        Items items = inventory.get(item);

        if(items.Category == Category.ESSENTIALS){
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
            return setNextChainResponsibility.calculateTotal(inventory,item , quantity,error,readEssentials,readLuxury,readMisc);
        }
    }
}