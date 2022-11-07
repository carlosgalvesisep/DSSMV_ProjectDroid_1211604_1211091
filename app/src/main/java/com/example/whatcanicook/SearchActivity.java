package com.example.whatcanicook;

import Adapters.RecipeAdapter;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import listeners.RandomRecipeResponseListener;
import models.RandomRecipeResponse;
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




    }
    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void fetch(RandomRecipeResponse response, String message) {
            recyclerView = findViewById(R.id.recycler_recipes);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 1));

            recipeAdapter = new RecipeAdapter(SearchActivity.this, response.recipes);
            recyclerView.setAdapter(recipeAdapter);
        }

        @Override
        public void error(String message) {
            Toast.makeText(SearchActivity.this, "error", Toast.LENGTH_SHORT).show();
        }
    };
}