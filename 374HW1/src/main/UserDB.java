package main;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDB {

    private static HashMap<Character, Double> codes;
    private static HashMap<Integer, Integer> cart;

    public UserDB() {
        codes = new HashMap<Character, Double>();
        cart = new HashMap<Integer, Integer>();
    }

    public static HashMap<Integer, Integer> getCartContents(String username) {
        return cart;
    }

    public static void addToCart(String username, int itemCode, int quantity) {
        cart.put(itemCode, quantity);
    }

    public static void removeFromCart(String username, int itemCode, int quantity) {
        Integer temp = cart.get(itemCode);
        if(temp != null){
            if(temp - quantity <= 0){
                cart.remove(itemCode);
            } else {
                cart.remove(itemCode);
                cart.put(itemCode, temp - quantity);
            }
        }
    }

    public static boolean getTaxPolicy(String username) {
        return false;
    }

    public static HashMap<Character, Double> getCodes(String username) {
        return codes;
    }

    public static double getTaxRate(String username) {
        return 0.05;
    }

    public static void addCode(String user, String code) {
        codes.put('M', 0.05);
    }

    public static int getAttempts(String user, int i) {
        return 3;
    }
}
