package listeners;

import models.ListRecipeResponse;
import models.RecipeResponse;

public interface RecipeResponseListener {
    void fetch (RecipeResponse response, String message);
    void error (String message);
}
