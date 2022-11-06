package com.example.whatcanicook;

import Adapters.RecipeAdapter;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import listeners.RandomRecipeResponseListener;
import models.RandomRecipeResponse;
import service.RequestService;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;


    public CardView card1, card2, card3, card4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 =(CardView) findViewById(R.id.your_fridge);
        card2 =(CardView) findViewById(R.id.search_activity);
        card3 =(CardView) findViewById(R.id.shopping_list);
        card4 =(CardView) findViewById(R.id.favourites);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);

        progressBar = new ProgressBar(this);
        progressBar.setVisibility(View.INVISIBLE);






    }





    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.your_fridge:
                i = new Intent(this, your_fridge.class);
                startActivity(i);
                break;
            case R.id.shopping_list:
                i = new Intent(this, shopping_list.class);
                startActivity(i);
                break;
            case R.id.favourites:
                i = new Intent(this, favourites.class);
                startActivity(i);
                break;
            case R.id.search_activity:
                i = new Intent(this, search_activity.class);
                startActivity(i);
                break;
            default:
        }
    }
}