package com.example.whatcanicook;

import Adapters.IngredientRecipeAdapter;
import Adapters.RandomRecipeAdapter;
import Adapters.RecipeAdapter;
import Adapters.SearchAdapter;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import listeners.RandomRecipeResponseListener;
import listeners.RecipeClickListener;
import listeners.RecipeResponseListener;
import listeners.SearchListener;
import models.*;
import service.RequestService;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    RecipeAdapter recipeAdapter;
    RandomRecipeAdapter randomRecipeAdapter;
    SearchAdapter searchAdapter;
    RecyclerView recyclerView;
    RequestService request;
    ProgressBar progressBar;
    EditText searchBtn;
    int fridge = 0;
    String[] ingredients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        progressBar = new ProgressBar(this);
        progressBar.setVisibility(View.INVISIBLE);

        searchBtn = findViewById(R.id.search_btn);


        request = new RequestService(this);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = searchBtn.getText().toString();
                request.searchRecipe(searchListener, input);
                fridge=3;
            }
        });


        if (getIntent().getExtras().getInt("id") == 1){
            ingredients=getIntent().getExtras().getStringArray("ingredients");
            request.getRecipes(recipeResponseListener, ingredients);
        } else if (fridge == 0) {
            request.getRandomRecipes(randomRecipeResponseListener);
        }


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

            randomRecipeAdapter = new RandomRecipeAdapter(SearchActivity.this, response.recipes, recipeClickListener);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void error(String message) {
            Toast.makeText(SearchActivity.this, "error", Toast.LENGTH_SHORT).show();
        }
    };

    private final RecipeResponseListener recipeResponseListener = new RecipeResponseListener() {
        @Override
        public void fetch(List<RecipeResponse> response, String message) {
            recyclerView = findViewById(R.id.recycler_recipes);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 1));

            recipeAdapter = new RecipeAdapter(SearchActivity.this, response, recipeClickListener);
            recyclerView.setAdapter(recipeAdapter);
        }

        @Override
        public void error(String message) {
            Toast.makeText(SearchActivity.this, "error", Toast.LENGTH_SHORT).show();
        }
    };

    private final SearchListener searchListener = new SearchListener() {
        @Override
        public void fetch(SearchResponse response, String message) {
            recyclerView = findViewById(R.id.recycler_recipes);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 1));

            searchAdapter = new SearchAdapter(SearchActivity.this, response.results, recipeClickListener);
            recyclerView.setAdapter(searchAdapter);
        }

        @Override
        public void error(String message) {

        }
    };


    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClick(String id) {
            startActivity(new Intent(SearchActivity.this, RecipeDetailsActivity.class)
                    .putExtra("id", id));
        }
    };

    private final RecipeClickListener recipeFromFridgeClickListener= new RecipeClickListener() {
        @Override
        public void onRecipeClick(String id) {

            //converter ArrayList<MissedIngredients> para ArrayList<String>

            startActivity(new Intent(SearchActivity.this, RecipeDetailsActivity.class)
                    .putExtra("id", id));
        }
    };


}