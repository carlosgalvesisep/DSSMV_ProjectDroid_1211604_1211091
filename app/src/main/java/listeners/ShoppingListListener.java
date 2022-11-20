package listeners;


import models.ShoppingListResponse;

public interface ShoppingListListener {
    void fetch (ShoppingListResponse response, String message);
    void error (String message);
}
