package models;

import java.util.ArrayList;

public class RecipeResponse {
    public int id;
    public String title;
    public String image;
    public String imageType;
    public int usedIngredientCount;
    public int missedIngredientCount;
    public ArrayList<MissedIngredient> missedIngredients;
    public ArrayList<UsedIngredient> usedIngredients;
    public ArrayList<Object> unusedIngredients;
    public int likes;
}
