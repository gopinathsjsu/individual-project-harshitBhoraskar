package cart_management.src.main.java.com.cart_management;

import java.util.HashMap;
import java.util.Set;

public class Misc implements Cart{

    private  Cart setCartCategory;

    @Override
    public void setCartCategory(Cart nextCart) {
        setCartCategory = nextCart;
    }

    @Override
    public double calculateTotal(HashMap<String, ItemInstance> cart , String item , int quantity, Set<String> wrongQuantities,
                                 Set<String> readEssentials,Set<String> readLuxury,Set<String> readMisc)    {
        ItemInstance items = cart.get(item);
        if(items.Category == "MISCELLENEOUS"){
            readMisc.add(item);
            if(quantity<=items.getQuantity()){
                EssentialTotal.setMiscSum(EssentialTotal.getMiscSum()-quantity);
                items.setQuantity(quantity);
                return quantity* items.getPrice();
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
