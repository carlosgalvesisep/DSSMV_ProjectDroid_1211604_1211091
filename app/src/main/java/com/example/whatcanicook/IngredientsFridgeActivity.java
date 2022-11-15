package com.example.whatcanicook;

import Adapters.IngridientFridgeAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import models.IngredientModel;

import java.util.ArrayList;

public class IngredientsFridgeActivity extends AppCompatActivity {

    RecyclerView recyclerIngredients;
    ArrayList<IngredientModel> ingredientModelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_fridge);

        recyclerIngredients = findViewById(R.id.recycler_ingredients);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerIngredients.setLayoutManager(layoutManager);


        ingredientModelArrayList = (ArrayList<IngredientModel>) getIntent().getExtras().getSerializable("list");

        recyclerIngredients.setAdapter(new IngridientFridgeAdapter(ingredientModelArrayList));

    }

}