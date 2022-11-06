package com.example.whatcanicook;

import Adapters.Ingredient_fridgeAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import models.IngredientModel;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class your_fridge extends AppCompatActivity {

    private RecyclerView recycler_ingredientesView;
    private Ingredient_fridgeAdapter ing_fridAdapter;
    private List<IngredientModel> ingList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_fridge);

        ingList = new ArrayList<>();
        recycler_ingredientesView = findViewById(R.id.recycler_ingredientes);
        recycler_ingredientesView.setLayoutManager( new LinearLayoutManager(this));

        ing_fridAdapter = new Ingredient_fridgeAdapter(this);
        recycler_ingredientesView.setAdapter(ing_fridAdapter);

        IngredientModel ingre = new IngredientModel();
        ingre.setName("ONLY TESTTTTTTTTTTTTTTTTTTTT");
        ingre.setStatus(0);
        ingre.setId(1);


        ingList.add(ingre);
        ingList.add(ingre);
        ingList.add(ingre);
        ingList.add(ingre);
        ingList.add(ingre);


        ing_fridAdapter.setIngredient(ingList);


    }
}