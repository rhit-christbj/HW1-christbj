package main;

public class ItemHandler {

    public boolean inStock(int itemCode, int quantity) {
        int numInStock = InventoryDB.checkStock(itemCode, quantity);
        //A call to the DB would be made to find out how
        //much of the item (identified by code) is available.
        return numInStock >= quantity;
    }

    public void moveToCart(int itemCode, int quantity) {
        if (inStock(itemCode, quantity)) {
            InventoryDB.removeItem(itemCode, quantity);
        }
    }

    public void moveFromCart(int itemCode, int quantity) {
        InventoryDB.addItem(itemCode, quantity);
    }

    public double getPrice(int itemCode) {
        return 0.0; //Would make a DB call and return the value.
    }
    public String getItem(int itemCode) {
        return "Apple"; //Would make a DB call and return the Items name.
    }
}
