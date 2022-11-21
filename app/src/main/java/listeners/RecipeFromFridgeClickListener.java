package listeners;

import models.MissedIngredient;

import java.util.ArrayList;

public interface RecipeFromFridgeClickListener {
    void onRecipeClick(String id, ArrayList<MissedIngredient> missedIngredients);
}
