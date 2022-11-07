package com.example.whatcanicook;

import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;

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
                i = new Intent(this, FridgeActivity.class);
                startActivity(i);
                break;
            case R.id.shopping_list:
                i = new Intent(this, ShoppingListActivity.class);
                startActivity(i);
                break;
            case R.id.favourites:
                i = new Intent(this, FavouritesActivity.class);
                startActivity(i);
                break;
            case R.id.search_activity:
                i = new Intent(this, SearchActivity.class);
                startActivity(i);
                break;
            default:
        }
    }
}