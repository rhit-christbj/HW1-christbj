package main;

public class ShoppingCartAPI {

    private final Cart cart;
    private final DiscountCodeHandler dch;

    public ShoppingCartAPI() {
        ItemHandler ih = new ItemHandler();
        this.cart = new Cart(ih);
        this.dch = new DiscountCodeHandler(cart, ih);
    }

    public ReturnMsg addItemToCart(String username, int itemCode, int quantity) {
        if (cart.add(username, itemCode, quantity)) {
            return new ReturnMsg("Item successfully added to cart", cart.getContent(username), cart.calcCost(username));
        } else {
            return new ReturnMsg("Sorry, that item is out of stock", cart.getContent(username), cart.calcCost(username));
        }
    }

    public ReturnMsg removeItemFromCart(String username, int itemCode, int quantity) {
        if (cart.remove(username, itemCode, quantity)) {
            return new ReturnMsg("Item successfully removed from cart", cart.getContent(username), cart.calcCost(username));
        } else {
            return new ReturnMsg("Error in removing item from cart", cart.getContent(username), cart.calcCost(username));
        }
    }

    public ReturnMsg applyDiscountCode(String username, String code) {
        return new ReturnMsg(dch.applyDiscountCode(username, code), cart.getContent(username), cart.calcCost(username));
    }

    public ReturnMsg viewCartContent(String username){
        return new ReturnMsg("Returned cart content", cart.getContent(username), cart.calcCost(username));
    }
}
