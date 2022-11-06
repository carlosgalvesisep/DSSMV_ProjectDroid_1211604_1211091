package listeners;

import models.RandomRecipeResponse;

public interface RandomRecipeResponseListener {
    void fetch(RandomRecipeResponse response, String message);
    void error (String message);
}
