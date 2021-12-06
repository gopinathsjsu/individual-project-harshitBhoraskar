package com.inventory;

import java.util.HashMap;
import java.util.Set;

public interface Chain {

    public void setNextChainOfResp(Chain nextChain);

    public double calculateTotal(HashMap<String, Items> inventory , String item , int quantity, Set<String> error,
                            Set<String> readEssentials,Set<String> readLuxury,Set<String> readMisc);


}
