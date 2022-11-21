package com.example.whatcanicook;


import Adapters.FridgeItemAdapter;
import Adapters.ShoppingListItemAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.IngredientModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {
    ArrayList<IngredientModel> mIngredientsList;
    private RecyclerView mRecyclerView;
    private ShoppingListItemAdapter mAdapter;

    private FirebaseAuth mauth;

    private RecyclerView.LayoutManager mLayoutManager;
    ImageView searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        mauth = FirebaseAuth.getInstance();
        loadData();
        buildRecyclerView();
        setInsertButton();


    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferencesSL", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mIngredientsList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferencesSL", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<IngredientModel>>() {}.getType();
        mIngredientsList = gson.fromJson(json, type);

        if (mIngredientsList == null) {
            mIngredientsList = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview16);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ShoppingListItemAdapter(mIngredientsList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ShoppingListItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //now delete..
                mIngredientsList.remove(position);
                saveData();
                //then notify..
                mAdapter.notifyItemRemoved(position);
            }
        });
    }

    private void setInsertButton() {
        ImageView buttonInsert = findViewById(R.id.button_insertSL);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mauth.getCurrentUser().isEmailVerified()){
                    EditText ingName = findViewById(R.id.ingredientNameSL);
                    EditText ingQuantity = findViewById(R.id.ingredientQuantitySL);

                    String line1= ingName.getText().toString().trim();
                    String line2= ingQuantity.getText().toString().trim();
                    if(line1.isEmpty()){
                        ingName.setError("You must add ingredient name");
                        ingName.requestFocus();
                    }else {
                        insertIngredient(ingName.getText().toString(), ingQuantity.getText().toString());
                        saveData();
                    }
                }else{
                    Toast.makeText(ShoppingListActivity.this,"Verify your email first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertIngredient(String line1, String line2) {
        mIngredientsList.add(new IngredientModel(line1, line2));
        mAdapter.notifyItemInserted(mIngredientsList.size());
    }}