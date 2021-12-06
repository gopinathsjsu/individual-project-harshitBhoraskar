package cart_management.src.main.java.com.cart_management;

import java.util.HashMap;
import java.util.Set;

public class Luxury implements Cart{

    private  Cart setCartCategory;

    @Override
    public void setCartCategory(Cart nextCart) {
        setCartCategory = nextCart;
    }

    @Override
    public double calculateTotal(HashMap<String, ItemInstance> cart , String item , int quantity, Set<String> wrongQuantities,
                                 Set<String> readEssentials,Set<String> readLuxury,Set<String> readMisc)
    {
        ItemInstance itemInstance = cart.get(item);
        if(itemInstance.Category == "Luxury"){
            readLuxury.add(item);
            if( quantity<=itemInstance.getQuantity()){
                EssentialTotal.setLuxurySum(  EssentialTotal.luxurySum() -quantity);
                itemInstance.setQuantity(quantity);
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
