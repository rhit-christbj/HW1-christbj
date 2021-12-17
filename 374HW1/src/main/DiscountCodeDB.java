package main;

import java.util.HashMap;

public class DiscountCodeDB {
    public static boolean isValid(String code) {
        return code.equals("code");
    }

    public static HashMap<Integer, Integer> getRequirements(String code) {
        if (code.equals("code")){
            HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
            temp.put(1, 1);
            return temp;
        }
        return null;
    }
}
