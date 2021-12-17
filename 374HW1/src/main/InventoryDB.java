package main;

import java.util.HashMap;

public class InventoryDB {

    private static HashMap<Integer, Integer> inventory;

    public InventoryDB() {
        inventory = new HashMap<Integer, Integer>();
        inventory.put(1, 3);
        inventory.put(2, 7);
        inventory.put(3, 2);
    }

    public static void removeItem(int itemCode, int quantity) {
        Integer temp = inventory.get(itemCode);
        if (temp != null) {
            if (temp - quantity <= 0) {
                inventory.remove(itemCode);
            } else {
                inventory.remove(itemCode);
                inventory.put(itemCode, temp - quantity);
            }
        }
    }

    public static void addItem(int itemCode, int quantity) {
        inventory.put(itemCode, quantity);
    }

    public static int checkStock(int itemCode, int quantity) {
        if (inventory.containsKey(itemCode)) {
            return inventory.get(itemCode);
        }
        return 0;
    }
}
