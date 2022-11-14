package com.example.whatcanicook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mauth;
    private Button logout_btn;



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

        logout_btn=findViewById(R.id.logout_btn);
        mauth=FirebaseAuth.getInstance();
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();

            }
        });



    }

    @Override
    public  void onStart(){
        super.onStart();
        FirebaseUser currentUser=mauth.getCurrentUser();
        if (currentUser==null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
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