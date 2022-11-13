package com.example.whatcanicook;

import Adapters.IngredientAdapter;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import listeners.RecipeDetailsListener;
import models.RecipeDetails;
import service.RequestService;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    TextView meal_name, meal_source, meal_description;
    ImageView meal_image;
    RecyclerView meal_ingredients;
    RequestService service;
    IngredientAdapter ingredientAdapter;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);


        meal_name = findViewById(R.id.meal_name);
        meal_source = findViewById(R.id.meal_source);
        meal_description = findViewById(R.id.meal_description);
        meal_image = findViewById(R.id.meal_image);
        meal_ingredients = findViewById(R.id.meal_ingredients);

        id=Integer.parseInt(getIntent().getStringExtra("id"));
        service  =new RequestService(this);
        service.getRecipeDetails(recipeDetailsListener, id);



    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void fetch(RecipeDetails response, String message) {
            meal_name.setText(response.title);
            meal_source.setText(response.sourceName);
            meal_description.setText(response.summary);
            Picasso.get().load(response.image).into(meal_image);

            meal_ingredients.setHasFixedSize(true);
            meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            ingredientAdapter = new IngredientAdapter(RecipeDetailsActivity.this, response.extendedIngredients);
            meal_ingredients.setAdapter(ingredientAdapter);

        }

        @Override
        public void error(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

}
