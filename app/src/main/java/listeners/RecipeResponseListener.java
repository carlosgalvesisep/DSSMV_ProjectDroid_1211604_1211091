package listeners;

import models.ListRecipeResponse;
import models.RecipeResponse;

import java.util.List;

public interface RecipeResponseListener {
    void fetch (List<RecipeResponse> response, String message);
    void error (String message);
}
