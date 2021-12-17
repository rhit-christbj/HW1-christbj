package main;

import java.util.HashMap;
import java.util.Map;

public class DiscountCodeHandler {

    private final Cart cart;
    private final ItemHandler ih;

    public DiscountCodeHandler(Cart cart, ItemHandler ih) {
        this.cart = cart;
        this.ih = ih;
    }

    public String applyDiscountCode(String username, String code) {
        if (DiscountCodeDB.isValid(code)) {
            HashMap<Integer, Integer> reqs = DiscountCodeDB.getRequirements(code);
            Map.Entry<Integer, Integer> res = cart.checkContents(username, reqs);
            if (res == null) {
                cart.addCode(username, code);
                return "Added Code: " + code;
            }
            return "Missing: " + res.getValue().toString() + ih.getItem(res.getKey());
        }
        return "Invalid Discount Code";
    }

    public int getRejectedAttempts(String user){
        return cart.getRejectedCodeAttempts(user);
    }
}
