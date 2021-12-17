package main;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private final ItemHandler ih;

    public Cart(ItemHandler ih) {
        this.ih = ih;
    }

    public Map.Entry<Integer, Integer> checkContents(String username, HashMap<Integer, Integer> items) {
        HashMap<Integer, Integer> cart = UserDB.getCartContents(username);
        //The HashMap would be populated with
        //<itemCode, quantity> information from DB.
        //This isn't meant for long term cart
        //information, merely a way to validate it.
        for (Map.Entry<Integer, Integer> set : items.entrySet()) {
            if (!cart.containsKey(set.getKey()))
                return set;
            if (cart.get(set.getKey()).compareTo(set.getValue()) >= 0)
                return set;
        }
        return null;
    }

    public boolean add(String username, int itemCode, int quantity) {
        if (ih.inStock(itemCode, quantity)) {
            UserDB.addToCart(username, itemCode, quantity);
            ih.moveToCart(itemCode, quantity);
            return true;
        }
        return false;
    }

    public boolean remove(String username, int itemCode, int quantity) {
        HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
        temp.put(itemCode, quantity);
        if (checkContents(username, temp) == null) {
            UserDB.removeFromCart(username, itemCode, quantity);
            ih.moveFromCart(itemCode, quantity);
            HashMap<String, Double> codes = UserDB.getCodes(username);
            for(Map.Entry<String,Double> set: codes.entrySet()){
                if(checkContents(username, DiscountCodeDB.getRequirements(set.getKey())) != null) {
                    UserDB.removeCode(username, set.getKey());
                }
            }
            return true;
        }
        return false;
    }

    public double calcCost(String username) {
        HashMap<Integer, Integer> cart = UserDB.getCartContents(username);
        double cost = 0;
        for (Map.Entry<Integer, Integer> set : cart.entrySet()) {
            cost += ih.getPrice(set.getKey());
        }
        boolean taxAfterDiscountCode = UserDB.getTaxPolicy(username);
        //Getting user locations and their respective tax laws falls
        //outside the scope of this API. I'm going to believe that User
        //location is stored in the database, and it's ust a matter of
        //calculation tax before or after discount codes are applied.
        double taxRate = UserDB.getTaxRate(username);
        HashMap<String, Double> codes = UserDB.getCodes(username);
        if(taxAfterDiscountCode){
            for (Map.Entry<String, Double> set : codes.entrySet()) {
                 cost -= set.getValue() * cost;
            }
            cost *= taxRate;
        } else {
            cost *= taxRate;
            for (Map.Entry<String, Double> set : codes.entrySet()) {
                cost *= set.getValue();
            }
        }
        return cost;
    }

    public void addCode(String user, String code) {
        UserDB.addCode(user, code);
    }

    public int getRejectedCodeAttempts(String user) {
        return UserDB.getAttempts(user, 24);
    }

    public HashMap<Integer, Integer> getContent(String username) {
        return UserDB.getCartContents(username);
    }
}
