package listeners;

import models.RecipeDetails;

public interface RecipeDetailsListener {
    void fetch (RecipeDetails response, String message);
    void error (String message);
}
