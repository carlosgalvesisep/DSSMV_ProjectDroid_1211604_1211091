package com.example.whatcanicook;

import Adapters.IngredientRecipeAdapter;
import Adapters.RecipeAdapter;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import listeners.RandomRecipeResponseListener;
import listeners.RecipeClickListener;
import listeners.RecipeResponseListener;
import models.ListRecipeResponse;
import models.RandomRecipeResponse;
import models.RecipeResponse;
import service.RequestService;

public class SearchActivity extends AppCompatActivity {

    RecipeAdapter recipeAdapter;
    RecyclerView recyclerView;
    RequestService request;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        progressBar = new ProgressBar(this);
        progressBar.setVisibility(View.INVISIBLE);


        request = new RequestService(this);
        request.getRandomRecipes(randomRecipeResponseListener);
        /*
        request = new RequestService(this);
        request.getRecipes(recipeResponseListener);
        */


    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void fetch(RandomRecipeResponse response, String message) {
            recyclerView = findViewById(R.id.recycler_recipes);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 1));

            recipeAdapter = new RecipeAdapter(SearchActivity.this, response.recipes, recipeClickListener);
            recyclerView.setAdapter(recipeAdapter);
        }

        @Override
        public void error(String message) {
            Toast.makeText(SearchActivity.this, "error", Toast.LENGTH_SHORT).show();
        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClick(String id) {
            startActivity(new Intent(SearchActivity.this, RecipeDetailsActivity.class)
                    .putExtra("id", id));
        }
    };
}