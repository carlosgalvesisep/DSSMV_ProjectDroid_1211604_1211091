package com.example.whatcanicook;


import Adapters.FridgeItemAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.IngredientModel;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FridgeActivity extends AppCompatActivity {
    ArrayList<IngredientModel> mIngredientsList;
    private RecyclerView mRecyclerView;
    private FridgeItemAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_fridge);

        loadData();
        buildRecyclerView();
        setInsertButton();

        searchBtn = findViewById(R.id.searchByIngredient);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putInt("id",1);
                String [] ingredients = {"salmon"};
                b.putStringArray("ingredients", ingredients);
                startActivity(new Intent(FridgeActivity.this, SearchActivity.class).putExtras(b));
            }
        });

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mIngredientsList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<IngredientModel>>() {}.getType();
        mIngredientsList = gson.fromJson(json, type);

        if (mIngredientsList == null) {
            mIngredientsList = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview15);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new FridgeItemAdapter(mIngredientsList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new FridgeItemAdapter.OnItemClickListener() {
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
        Button buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText line1 = findViewById(R.id.edit_ingredientName);
                EditText line2 = findViewById(R.id.edit_ingredientQuantity);
                insertIngredient(line1.getText().toString(), line2.getText().toString());
            }
        });
    }

    private void insertIngredient(String line1, String line2) {
        mIngredientsList.add(new IngredientModel(line1, line2));
        mAdapter.notifyItemInserted(mIngredientsList.size());
    }}